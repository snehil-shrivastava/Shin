package anime.stream.shin.base.network
import anime.stream.shin.base.network.models.AnimeDataModel
import anime.stream.shin.base.network.models.AnimeEpisodeDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface AnimeNetworkprovider {

    @GET("RecentlyAddedSeries")
    fun getRecentlyAddedSeries():Call<List<AnimeDataModel>>


    @GET("RecentReleaseEpisodes/1")
    fun getRecentReleaseEpisodes():Call<List<AnimeEpisodeDataModel>>


    @GET("Search/{name}")
    fun getSearchAnime(@Path("name") name: String):Call<AnimeDataModel>


    @GET("AnimeEpisodeHandler/{id}")
    fun getAnimeEpisodes(@Path("id") id : String):Call<AnimeEpisodeDataModel>


    @GET("OngoingSeries/1")
    fun getOnGoingSeries(): Call<List<AnimeDataModel>>


    @GET("Popular/1")
    fun getPopularAnime():Call<List<AnimeDataModel>>




}