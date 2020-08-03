package anime.stream.core.services.models.manga

/*
 * Must call set ID after getting the object instance
 */

interface Manga {
    fun title(): String
    fun rating(): String
    fun coverUrl(): String
    val id: String
}

fun Manga.setId(id_: String): Manga {
    return object : Manga {
        override fun title() = this@setId.title()

        override fun rating() = this@setId.rating()

        override fun coverUrl() = this@setId.coverUrl()

        override val id: String
            get() = id_
    }
}
