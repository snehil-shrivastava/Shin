package anime.stream.network.manga.mangadex.models


import anime.stream.core.services.models.manga.Titles

data class MangaDexTitles(
    override val cover: String?,
    override val mangaName: String?,
    override val mangaId: String?,
    override val rating: String?,
    override val time: String?,
    override val chapterName: String?,
    override val chapterId: String?
) : Titles