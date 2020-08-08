package anime.stream.network.animekisa

import anime.stream.core.services.ServerToLinkService
import anime.stream.network.di.DaggerNetworkComponent
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AnimeNetworkTest {

    companion object {
        lateinit var service: AnimeKisaService
        lateinit var parser: ServerToLinkService
    }

    @Before
    fun initialise() {
        val dag = DaggerNetworkComponent.factory().create()
        service = dag.animeKisaService
        parser = dag.IServerToLinkService
    }

    @Test
    fun getVideoProvidersUrl() {
        val item = service.getEpisode("sekai-douwa-anime-zenshuu-episode-10").blockingFirst()

        for (i in item.getServerList()) {
            println("url: ${i.embeddedUrl}")
            val link = parser.getVideoLinksFromServer(i).blockingFirst().getLinks()

            for (t in link) {
                println(t.url)
            }
        }
        Assert.assertTrue(
            item.toString(), false
        )
    }
}
