package anime.stream.libnetwork.manga.mangadex.models

import anime.stream.libnetwork.manga.commons.models.Collection
import anime.stream.libnetwork.manga.commons.models.Titles
import anime.stream.libnetwork.manga.mangadex.util.Utils.getChapterId
import anime.stream.libnetwork.manga.mangadex.util.Utils.getMangaId
import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.annotation.Selector
import java.util.regex.Pattern

class MangaDexCollection : Collection {

    @Selector("#latest_update > div")
    private lateinit var mangaUpdatesElement: Element

    @Selector("#week > ul")
    private lateinit var topChaptersElement: Element

    @Selector("#top_follows > ul")
    private lateinit var topMangaElement: Element

    @Selector("#hled_titles_owl_carousel")
    private lateinit var featuredElement: Element

    @Selector("#new_titles_owl_carousel")
    private lateinit var newElement: Element

    private val collection = Array(5) { mutableListOf<MangaDexTitles>() }

    fun process() {
        collection[UPDATES] = parseMangaUpdates(mangaUpdatesElement)
        collection[TOP_MANGA] = parseTopManga(topMangaElement)
        collection[FEATURED] = parseFeatured(featuredElement)
        collection[NEW] = parseNew(newElement)
        collection[TOP_CHAPTERS] = parseTopChapters(topChaptersElement)
    }

    private fun parseNew(new: Element): MutableList<MangaDexTitles> {
        val mList = ArrayList<MangaDexTitles>()
        for (li in new.children()) {
            val img = li.select("div > div.hover > a > img")
            val manga = li.select("div > div.car-caption.px-2.py-1 > p.text-truncate.m-0 > a")
            val chapter = li.select("div > div.car-caption.px-2.py-1 > p:nth-child(2) > a")
            val time = li.select("div > div.car-caption.px-2.py-1 > p:nth-child(2) > span > small").text()

            mList.add(
                MangaDexTitles(
                    img.attr("data-src"),
                    manga.text(),
                    manga.attr("href").getMangaId(),
                    "",
                    time,
                    chapter.text(),
                    chapter.attr("href").getChapterId()
                )
            )
        }
        return mList
    }

    private fun parseTopChapters(topChapters: Element): MutableList<MangaDexTitles> {
        val mList = ArrayList<MangaDexTitles>()
        for (li in topChapters.children()) {
            val img = li.select("div.hover.tiny_logo.rounded.float-left.mr-2 > a > img")
            val manga = li.select("div.text-truncate.pt-0.pb-1.mb-1.border-bottom > a")
            val chapter = li.select("p > span.float-left > a")

            mList.add(
                MangaDexTitles(
                    img.attr("src"),
                    manga.text(),
                    manga.attr("href").getMangaId(),
                    "",
                    "",
                    chapter.text(),
                    chapter.attr("href").getChapterId()
                )
            )
        }
        return mList
    }

    private fun parseFeatured(featured: Element): MutableList<MangaDexTitles> {
        val mList = ArrayList<MangaDexTitles>()
        for (li in featured.children()) {
            val img = li.select("div > div.hover > a > img")
            val manga = li.select("div > div.car-caption.px-2.py-1 > p.text-truncate.m-0 > a")
            val rating = li.select("div > div.car-caption.px-2.py-1 > p:nth-child(2) > span.float-right").text()

            mList.add(
                MangaDexTitles(
                    img.attr("data-src"),
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

    private fun parseTopManga(hotChapters: Element): MutableList<MangaDexTitles> {
        val mList = ArrayList<MangaDexTitles>()
        for (li in hotChapters.children()) {
            val img = li.select("div.hover.tiny_logo.rounded.float-left.mr-2 > a > img")
            val manga = li.select("div.text-truncate.pt-0.pb-1.mb-1.border-bottom > a")
            val rating = li.select("p > span.float-right > span")

            mList.add(
                MangaDexTitles(
                    img.attr("src"),
                    manga.text(),
                    manga.attr("href").getMangaId(),
                    rating.text(),
                    "",
                    "",
                    ""
                )
            )
        }
        return mList
    }

    private fun parseMangaUpdates(elm: Element): MutableList<MangaDexTitles> {
        val mList = ArrayList<MangaDexTitles>()
        for (li in elm.children()) {
            val img = li.select("div.hover.sm_md_logo.rounded.float-left.mr-2 > a > img")
            val manga = li.select("div.pt-0.pb-1.mb-1.border-bottom.d-flex.align-items-center.flex-nowrap > a")
            val chapter = li.select("div.py-0.mb-1.row.no-gutters.align-items-center.flex-nowrap > a")
            val time = li.select("div:nth-child(5)").text()

            mList.add(
                MangaDexTitles(
                    img.attr("src"),
                    manga.text(),
                    manga.attr("href").getMangaId(),
                    "",
                    time,
                    chapter.text(),
                    chapter.attr("href").getChapterId()
                )
            )
        }
        return mList
    }

    companion object {

        private val COLLECTION_NAMES = arrayOf(
            "Manga Updates",
            "Top Manga", "Top Chapters", "Featured", "New"
        )

        private const val UPDATES = 0
        private const val TOP_MANGA = 1
        private const val TOP_CHAPTERS = 2
        private const val FEATURED = 3
        private const val NEW = 4
    }

    override fun getCollectionIds() = arrayOf(UPDATES, TOP_MANGA, TOP_CHAPTERS, FEATURED, NEW)

    override fun getCollectionName(id: Int) = COLLECTION_NAMES[id]

    override fun getList(id: Int): MutableList<MangaDexTitles> = collection[id]

    override fun preProcess() {
        process()
    }
}