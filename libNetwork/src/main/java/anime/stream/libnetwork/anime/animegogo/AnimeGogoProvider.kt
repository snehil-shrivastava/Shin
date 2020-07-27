package anime.stream.libnetwork.anime.animegogo

import anime.stream.libnetwork.anime.commons.AnimeNetworkProvider
import anime.stream.libnetwork.anime.commons.model.AnimeDataModel
import anime.stream.libnetwork.anime.commons.model.AnimeEpisodeDataModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeGogoProvider : AnimeNetworkProvider {
    @GET("RecentlyAddedSeries")
    override fun getRecentlyAddedSeries(): Observable<List<AnimeDataModel>>

    @GET("RecentReleaseEpisodes/1")
    override fun getRecentReleaseEpisodes(): Observable<List<AnimeEpisodeDataModel>>

    // Todo : add paging information
    @GET("Search/{name}")
    override fun searchAnime(@Path("name") name: String): Observable<AnimeDataModel>

    @GET("AnimeEpisodeHandler/{id}")
    override fun getAnimeEpisodes(@Path("id") id: String): Observable<AnimeEpisodeDataModel>

    @GET("OngoingSeries/1")
    override fun getOnGoingSeries(): Observable<List<AnimeDataModel>>

    @GET("Popular/1")
    override fun getPopularAnime(): Observable<List<AnimeDataModel>>
}