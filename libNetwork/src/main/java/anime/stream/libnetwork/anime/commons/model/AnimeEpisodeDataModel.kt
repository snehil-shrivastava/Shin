package anime.stream.libnetwork.anime.commons.model

data class AnimeEpisodeDataModel(
    val category: String,
    val genres: List<String>,
    val servers: List<Server>,
    val img: String,
    val otherName: String,
    val released: Int,
    val status: String,
    val synopsis: String,
    val totalEpisodes: Int
)