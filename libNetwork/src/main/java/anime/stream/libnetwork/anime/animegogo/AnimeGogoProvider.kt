package anime.stream.libnetwork.anime.animegogo

import anime.stream.libnetwork.anime.commons.AnimeNetworkProvider
import anime.stream.libnetwork.anime.commons.model.AnimeDataModel
import anime.stream.libnetwork.anime.commons.model.AnimeEpisodeDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeGogoProvider : AnimeNetworkProvider {
    @GET("RecentlyAddedSeries")
    override fun getRecentlyAddedSeries(): Call<List<AnimeDataModel>>

    @GET("RecentReleaseEpisodes/1")
    override fun getRecentReleaseEpisodes(): Call<List<AnimeEpisodeDataModel>>

    @GET("Search/{name}")
    override fun getSearchAnime(@Path("name") name: String): Call<AnimeDataModel>

    @GET("AnimeEpisodeHandler/{id}")
    override fun getAnimeEpisodes(@Path("id") id: String): Call<AnimeEpisodeDataModel>

    @GET("OngoingSeries/1")
    override fun getOnGoingSeries(): Call<List<AnimeDataModel>>

    @GET("Popular/1")
    override fun getPopularAnime(): Call<List<AnimeDataModel>>
}