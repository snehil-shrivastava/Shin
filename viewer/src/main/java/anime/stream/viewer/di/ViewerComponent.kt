package anime.stream.viewer.di

import android.content.Context
import anime.stream.core.di.CoreModule
import anime.stream.core.di.InjectingSavedStateViewModelFactory
import anime.stream.core.services.FavouriteService
import anime.stream.core.services.MangaNetworkService
import dagger.BindsInstance
import dagger.Component

@ViewerScope
@Component(
    modules = [AssistedModule::class, ViewerModule::class, CoreModule::class]
)
interface ViewerComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance networkService: MangaNetworkService,
            @BindsInstance favouriteService: FavouriteService,
            @BindsInstance app: Context
        ): ViewerComponent
    }

    val viewModel: InjectingSavedStateViewModelFactory
}