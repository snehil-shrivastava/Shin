package anime.stream.network.di

import anime.stream.network.manga.mangadex.MangaDexService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModuleProduction::class, NetworkModuleDebug::class]
)
interface NetworkComponent {

    @Component.Factory
    interface Factory {
        fun create(): NetworkComponent
    }

    val debugMangaDexProvider: MangaDexService
}