package anime.stream.shin.base.network.models

data class AnimeDataModel(
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