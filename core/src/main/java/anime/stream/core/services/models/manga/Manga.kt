package anime.stream.core.services.models.manga

/*
 * Must call set ID after getting the object instance
 */

interface Manga {
    fun title(): String
    fun rating(): String
    fun coverUrl(): String
    fun author(): String
    fun isAdult(): Boolean
    fun lastChapter(): String
    fun description(): String
    fun chapters(): ArrayList<Pair<Int, String>>
    val id: String
}

fun Manga.setId(id_: String): Manga {
    return object : Manga by this {
        override val id: String
            get() = id_
    }
}
