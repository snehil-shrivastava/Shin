package anime.stream.network.di

import anime.stream.network.animekisa.AnimeKisaService
import anime.stream.network.manga.mangadex.MangaDexService
import anime.stream.network.parser.IServerToLink
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

    val mangaDexProvider: MangaDexService

    val animeKisaService: AnimeKisaService

    val IServerToLinkService: IServerToLink

}
