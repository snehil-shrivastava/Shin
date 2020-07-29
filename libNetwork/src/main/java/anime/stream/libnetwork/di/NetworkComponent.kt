package anime.stream.libnetwork.di

import anime.stream.libnetwork.manga.mangadex.MangaDexProvider
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

    val debugMangaDexProvider: MangaDexProvider
}