package anime.stream.network.anime.animegogo.model

data class AnimeModel(
    val episodes: List<Episode>,
    val genres: List<String>,
    val img: String,
    val otherName: String,
    val released: Int,
    val status: String,
    val synopsis: String,
    val title: String,
    val totalEpisodes: Int
)