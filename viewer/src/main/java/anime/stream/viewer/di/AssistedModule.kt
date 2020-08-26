package anime.stream.viewer.di

import androidx.lifecycle.ViewModel
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import anime.stream.core.di.ViewModelKey
import anime.stream.viewer.viewmodels.MangaViewerViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_AssistedModule::class])
abstract class AssistedModule {
    @Binds
    @IntoMap
    @ViewModelKey(MangaViewerViewModel::class)
    abstract fun bindVMFactory(f: MangaViewerViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}