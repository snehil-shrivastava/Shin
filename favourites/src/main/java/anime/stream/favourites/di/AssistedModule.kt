package anime.stream.favourites.di


import androidx.lifecycle.ViewModel
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import anime.stream.core.di.ViewModelKey
import anime.stream.favourites.viewmodels.FavouritesViewModel
import anime.stream.favourites.viewmodels.MangaViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_AssistedModule::class])
abstract class AssistedModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    abstract fun bindVMFactory(f: FavouritesViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>


    @Binds
    @IntoMap
    @ViewModelKey(MangaViewModel::class)
    abstract fun bindVM2Factory(f: MangaViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}