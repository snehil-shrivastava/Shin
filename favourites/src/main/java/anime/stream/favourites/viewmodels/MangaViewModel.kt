package anime.stream.favourites.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import anime.stream.favourites.repository.MangaFavouritesRepository
import anime.stream.favourites.room.MangaFavourites
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import timber.log.Timber

class MangaViewModel @AssistedInject
constructor(
    private val repository: MangaFavouritesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(),
    Consumer<PagingData<MangaFavourites>> {

    private val mMangaFavorites = MutableLiveData<PagingData<MangaFavourites>>()
    private val mBusy = MutableLiveData<Boolean>()

    val mangaFavourites: LiveData<PagingData<MangaFavourites>> = mMangaFavorites

    private val composite = CompositeDisposable()

    init {
        refreshList()
    }

    private fun refreshList() {
        composite.add(repository.getAllMangaFav().cachedIn(viewModelScope).subscribe(this))
    }

    override fun onCleared() {
        composite.dispose()
        composite.clear()
        super.onCleared()
    }


    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MangaViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MangaViewModel
    }

    override fun accept(t: PagingData<MangaFavourites>?) {
        t?.let {
            mMangaFavorites.postValue(it)
        }
    }

    fun removeMangaItem(id: String) {
        composite.add(repository.removeFromMangaFavourite(id).subscribe({}, {
            Timber.e(it)
        }))
        refreshList()
    }
}