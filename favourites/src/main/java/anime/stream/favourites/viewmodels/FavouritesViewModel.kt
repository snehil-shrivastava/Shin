package anime.stream.favourites.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.SavedStateHandle
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import anime.stream.favourites.util.Constants.SEARCH_STATE
import anime.stream.favourites.util.Constants.TAB_STATE
import anime.stream.favourites.util.ObservableViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class FavouritesViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ObservableViewModel() {

    val searchText = ObservableField<String>()

    val mState = ObservableField<Boolean>()

    init {
        mState.set(savedStateHandle[TAB_STATE] ?: true)
        searchText.set(savedStateHandle[SEARCH_STATE] ?: "")
    }

    fun selectAnimePage() {
        mState.set(true)
        savedStateHandle[TAB_STATE] = mState.get()
    }

    fun selectMangaPage() {
        mState.set(false)
        savedStateHandle[TAB_STATE] = mState.get()
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<FavouritesViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): FavouritesViewModel  // may be ommited prior kotlin 1.3.60 or after PR #121 in AssistedInject lib
    }

}