package anime.stream.libnetwork.anime.animegogo

import anime.stream.libnetwork.anime.commons.AnimeNetworkProvider
import anime.stream.shin.base.commons.model.AnimeEpisodeMain
import anime.stream.shin.base.commons.model.AnimeMain
import anime.stream.shin.base.commons.model.AnimePopularMain
import anime.stream.shin.base.commons.model.AnimeSearchMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*
import io.reactivex.Observable

interface AnimeGogoProvider : AnimeNetworkProvider {
    @GET("RecentlyAddedSeries")
    override fun getRecentlyAddedSeries(): Observable<AnimeMain>

    @GET("RecentReleaseEpisodes/{page}")
    fun getRecentReleaseEpisodes(@Path("page") page:String): Observable<AnimeEpisodeMain>

    @GET("Search/{name}")
    override fun getSearchAnime(@Path("name") name: String): Observable<AnimeSearchMain>

    @GET("AnimeEpisodeHandler/{id}")
    override fun getAnimeEpisodes(@Path("id") id: String): Observable<AnimeEpisodeMain>

    @GET("Popular/{page}")
    override fun getPopularAnime(@Path("page") page:String): Observable<AnimePopularMain>
}