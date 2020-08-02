package anime.stream.libnetwork.anime.animegogo.model

data class AnimeDataModel(
    val id: String,
    val episodes: List<String>,
    val genres: List<String>,
    val img: String,
    val otherName: String,
    val released: Int,
    val status: String,
    val synopsis: String,
    val title: String,
    val totalEpisodes: Int
)