package anime.stream.favourites.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import anime.stream.favourites.repository.MangaFavouritesRepository
import anime.stream.favourites.room.MangaFavourites
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber

class MangaViewModel @AssistedInject
constructor(
    private val repository: MangaFavouritesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val composite = CompositeDisposable()

    @ExperimentalCoroutinesApi
    private val mChannel = ConflatedBroadcastChannel("")

    @FlowPreview
    @ExperimentalCoroutinesApi
    val pagingData: Flow<PagingData<MangaFavourites>> = mChannel.asFlow().flatMapLatest {
        val data = if (it.isNotBlank()) {
            repository.searchMangaFav(it).cachedIn(viewModelScope).debounce(200)
        } else {
            repository.getAllMangaFav().cachedIn(viewModelScope)
        }
        data
    }

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

    fun searchManga(query: String) {
        mChannel.offer(query)
    }
}
