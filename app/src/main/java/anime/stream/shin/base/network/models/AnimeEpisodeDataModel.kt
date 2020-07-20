package anime.stream.shin.base.network.models

data class AnimeEpisodeDataModel(
    val category: String,
    val genres: List<String>,
    val img: String,
    val otherName: String,
    val released: Int,
    val servers: List<Server>,
    val status: String,
    val synopsis: String,
    val totalEpisodes: Int
)