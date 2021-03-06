package anime.stream.libnetwork.manga.mangadex.models

import anime.stream.libnetwork.manga.commons.models.Titles

data class MangaDexTitles(
    override val cover: String?,
    override val mangaName: String?,
    override val mangaId: String?,
    override val rating: String?,
    override val time: String?,
    override val chapterName: String?,
    override val chapterId: String?
) : Titles