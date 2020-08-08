package anime.stream.network.manga.mangadex

import anime.stream.core.services.models.manga.Collection
import anime.stream.network.di.DaggerNetworkComponent
import org.junit.Assert
import org.junit.Test

class MangaDexNetworkTest {

    @Test
    fun searchTest() {
        val provider = DaggerNetworkComponent.factory().create().mangaDexProvider
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
