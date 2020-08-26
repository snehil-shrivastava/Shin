package anime.stream.home.manga.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import anime.stream.home.manga.room.CollectionEntity
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.Flow

class MangaCollectionViewModel @AssistedInject
constructor(
    private val repository: MangaCollectionRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val composite = CompositeDisposable()

    val pagingData: Flow<PagingData<CollectionEntity>> =
        repository.getAllMangaFav()

    override fun onCleared() {
        composite.dispose()
        super.onCleared()
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MangaCollectionViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MangaCollectionViewModel
    }
}