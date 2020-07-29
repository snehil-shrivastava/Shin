package anime.stream.libnetwork.manga.mangadex

import anime.stream.libnetwork.di.DaggerNetworkComponent
import anime.stream.libnetwork.manga.commons.models.Collection
import org.junit.Assert
import org.junit.Test

class MangaDexNetworkTest {

    @Test
    fun searchTest() {
        val provider = DaggerNetworkComponent.factory().create().debugMangaDexProvider
        provider.searchManga("Dragon Ball", 0).blockingForEach { manga ->
            manga?.let { it ->
                (it as Collection).let { safe ->
                    safe.preProcess()
                    val res = safe.getList(safe.getCollectionIds()[0])[1].mangaId ?: return@blockingForEach
                    provider.fetchMangaDetails(res).blockingForEach { mdm ->
                        Assert.assertTrue(mdm.manga.description, !mdm.manga.title.isBlank())
                    }
                    Assert.assertTrue(res, !res.isBlank())
                }
            }
        }
    }
}
