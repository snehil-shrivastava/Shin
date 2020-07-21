package anime.stream.libnetwork.anime.commons

import anime.stream.libnetwork.anime.commons.model.AnimeDataModel
import anime.stream.libnetwork.anime.commons.model.AnimeEpisodeDataModel
import retrofit2.Call

/*
* An
* */

interface AnimeNetworkProvider {

    fun getRecentlyAddedSeries(): Call<List<AnimeDataModel>>

    fun getSearchAnime(name: String): Call<AnimeDataModel>

    fun getOnGoingSeries(): Call<List<AnimeDataModel>>

    fun getPopularAnime(): Call<List<AnimeDataModel>>

    fun getAnimeEpisodes(id: String): Call<AnimeEpisodeDataModel>

    fun getRecentReleaseEpisodes(): Call<List<AnimeEpisodeDataModel>>

}

