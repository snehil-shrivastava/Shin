package anime.stream.network.anime.animegogo

import anime.stream.network.anime.animegogo.model.AnimeEpisodeMain
import anime.stream.network.anime.animegogo.model.AnimeMain
import anime.stream.network.anime.animegogo.model.AnimePopularMain
import anime.stream.network.anime.animegogo.model.AnimeSearchMain
import anime.stream.network.anime.commons.AnimeNetworkProvider
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeGogoProvider : AnimeNetworkProvider {
    @GET("RecentlyAddedSeries")
    override fun getRecentlyAddedSeries(): Observable<AnimeMain>

    @GET("RecentReleaseEpisodes/{page}")
    fun getRecentReleaseEpisodes(@Path("page") page: String): Observable<AnimeEpisodeMain>

    @GET("Search/{name}")
    override fun getSearchAnime(@Path("name") name: String): Observable<AnimeSearchMain>

    @GET("AnimeEpisodeHandler/{id}")
    override fun getAnimeEpisodes(@Path("id") id: String): Observable<AnimeEpisodeMain>

    @GET("Popular/{page}")
    override fun getPopularAnime(@Path("page") page: String): Observable<AnimePopularMain>
}