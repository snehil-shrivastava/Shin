package anime.stream.favourites.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import anime.stream.favourites.repository.MangaFavouritesRepository
import anime.stream.favourites.room.MangaFavourites
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class MangaViewModel @AssistedInject
constructor(
    private val repository: MangaFavouritesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mBusy = MutableLiveData<Boolean>()

    private val composite = CompositeDisposable()

    val pagingData: Flow<PagingData<MangaFavourites>> =
        repository.getAllMangaFav()

    override fun onCleared() {
        composite.dispose()
        super.onCleared()
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MangaViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MangaViewModel
    }

    fun removeMangaItem(id: String) {
        composite.add(repository.removeFromMangaFavourite(id).subscribe({}, {
            Timber.e(it)
        }))
    }
}