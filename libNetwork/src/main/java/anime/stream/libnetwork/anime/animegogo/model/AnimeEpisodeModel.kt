package anime.stream.shin.base.commons.model

data class AnimeEpisodeModel(
    val category: String,
    val episode: Int,
    val genres: List<String>,
    val img: String,
    val otherName: String,
    val released: Int,
    val servers: List<Server>,
    val status: String,
    val synopsis: String,
    val title: String,
    val totalEpisodes: Int
)