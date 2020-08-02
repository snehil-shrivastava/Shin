package anime.stream.favourites.di

import android.content.Context
import anime.stream.core.di.CoreModule
import anime.stream.core.di.InjectingSavedStateViewModelFactory
import anime.stream.core.services.MangaNetworkService
import anime.stream.favourites.service.impl.FavouriteServiceImpl
import dagger.BindsInstance
import dagger.Component

@FavouriteScope
@Component(
    modules = [AssistedModule::class, FavouritesModule::class, CoreModule::class]
)
interface FavouriteComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance networkService: MangaNetworkService, @BindsInstance app: Context): FavouriteComponent
    }

    val viewModel: InjectingSavedStateViewModelFactory

    /** Use this to add, remove or check if a Favourite exits
     * @see [anime.stream.core.services.FavouriteService] **/
    val serviceImpl: FavouriteServiceImpl

    val networkService: MangaNetworkService
}