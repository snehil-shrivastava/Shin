package anime.stream.network.manga.mangadex.models

import anime.stream.core.services.models.Collection
import anime.stream.network.manga.mangadex.util.Utils.getMangaId
import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.annotation.Selector

class MangaDexSearchCollection : Collection {

    @Selector("#content > div.row.mt-1.mx-0")
    private lateinit var main: Element

    private var collection = ArrayList<MangaDexTitles>()

    private fun getSearchItems(main: Element): ArrayList<MangaDexTitles> {
        val mList = ArrayList<MangaDexTitles>()
        for (div in main.children()) {
            val img = div.select("div.rounded.large_logo.mr-2 > a > img")
            val manga = div.select("div.text-truncate.mb-1.d-flex.flex-nowrap.align-items-center > a")
            val rating = div.select("ul > li.list-inline-item.text-primary > span:nth-child(3)").text()

            mList.add(
                MangaDexTitles(
                    img.attr("src"),
                    manga.text(),
                    manga.attr("href").getMangaId(),
                    rating,
                    "",
                    "",
                    ""
                )
            )
        }
        return mList
    }

    companion object {

        private val COLLECTION_NAMES = arrayOf(
            "Search Results"
        )

        private const val SEARCH_RESULTS = 0
    }

    override fun getCollectionIds() = arrayOf(SEARCH_RESULTS)

    override fun getCollectionName(id: Int) = COLLECTION_NAMES[id]

    override fun getList(id: Int) = collection

    override fun preProcess() {
        collection = getSearchItems(main)
    }
}
