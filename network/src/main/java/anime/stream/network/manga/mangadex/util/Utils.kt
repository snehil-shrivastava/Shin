package anime.stream.network.manga.mangadex.util

import java.util.regex.Pattern

object Utils {
    fun String.getMangaId(): String {
        val matches = mangaId.matcher(this)
        return if (matches.find()) matches.group(2) ?: "" else ""
    }

    fun String.getChapterId(): String {
        val matches = chapterId.matcher(this)
        return if (matches.find()) matches.group(2) ?: "" else ""
    }

    @JvmStatic
    private val mangaId = Pattern.compile("(/title/)([0-9]*)")

    @JvmStatic
    private val chapterId = Pattern.compile("(/chapter/)([0-9]*)")

}
