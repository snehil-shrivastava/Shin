package anime.stream.libnetwork.anime.animegogo.model

import anime.stream.shin.base.commons.model.Server

data class AnimeEpisodeDataModel(
    val id: String,
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