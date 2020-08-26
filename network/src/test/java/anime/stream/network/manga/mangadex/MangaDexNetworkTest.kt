package anime.stream.network.manga.mangadex

import anime.stream.network.di.DaggerNetworkComponent
import org.junit.Before
import org.junit.Test

class MangaDexNetworkTest {

    companion object {
        lateinit var provider: MangaDexService
    }

    @Before
    fun test() {
        provider = DaggerNetworkComponent.factory().create().mangaDexProvider
    }

    @Test(timeout = 3300)
    fun searchTest() {
        provider.getMangaTitles().blockingFirst()
    }
}
