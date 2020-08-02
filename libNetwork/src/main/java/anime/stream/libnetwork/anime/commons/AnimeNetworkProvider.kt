package anime.stream.libnetwork.anime.commons

import anime.stream.shin.base.commons.model.AnimeEpisodeMain
import anime.stream.shin.base.commons.model.AnimeMain
import anime.stream.shin.base.commons.model.AnimePopularMain
import anime.stream.shin.base.commons.model.AnimeSearchMain
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/*
* An
* */

interface AnimeNetworkProvider {

    fun getRecentlyAddedSeries(): Observable<AnimeMain>

    fun getSearchAnime(name: String): Observable<AnimeSearchMain>


    fun getAnimeEpisodes(id: String): Observable<AnimeEpisodeMain>

    fun getRecentReleaseEpisodes(): Call<AnimeEpisodeMain>

    @GET(value = "Popular/{page}")
    fun getPopularAnime(@Path(value = "page") page: String): Observable<AnimePopularMain>
    @GET(value = "OngoingSeries/{page}")
    fun getOnGoingSeries(@Path(value = "page") page: String): Call<AnimeMain>
}

