package anime.stream.core.services.models.manga

interface Titles {
    val cover: String?
    // Hide view(View.GONE) when null or blank
    val mangaName: String?

    val mangaId: String?

    val rating: String?

    val time: String?

    val chapterName: String?

    val chapterId: String?
}
