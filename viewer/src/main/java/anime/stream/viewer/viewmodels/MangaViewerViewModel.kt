package anime.stream.viewer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import anime.stream.core.services.models.manga.Manga
import anime.stream.viewer.repository.MangaViewerRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer


class MangaViewerViewModel @AssistedInject
constructor(
    repository: MangaViewerRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val compose = CompositeDisposable()

    private var mangaId: String? = null

    private val mManga = MutableLiveData<Manga>()
    private val mError = MutableLiveData<String>()

    private val onErrorHandle = Consumer<Throwable?> { t -> t?.let { mError.postValue(t.message) } }
    private val onNextHandle = Consumer<Manga?> { t -> t?.let { mManga.postValue(t) } }

    val manga: LiveData<Manga> get() = mManga

    init {
        mangaId = savedStateHandle["mangaId"]
        if (mangaId != null) {
            compose.add(repository.loadManga(mangaId!!).subscribe(onNextHandle, onErrorHandle))
        }
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MangaViewerViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MangaViewerViewModel
    }


}