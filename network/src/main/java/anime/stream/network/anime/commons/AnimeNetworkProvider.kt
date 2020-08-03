package anime.stream.network.anime.commons

import anime.stream.network.anime.animegogo.model.AnimeEpisodeMain
import anime.stream.network.anime.animegogo.model.AnimeMain
import anime.stream.network.anime.animegogo.model.AnimePopularMain
import anime.stream.network.anime.animegogo.model.AnimeSearchMain
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/*
* An
* */

interface AnimeNetworkProvider {

    fun getRecentlyAddedSeries(): Observable<AnimeMain>

    fun getSearchAnime(name: String): Observable<AnimeSearchMain>


    fun getAnimeEpisodes(id: String): Observable<AnimeEpisodeMain>

    fun getRecentReleaseEpisodes(): Observable<AnimeEpisodeMain>

    @GET(value = "Popular/{page}")
    fun getPopularAnime(@Path(value = "page") page: String): Observable<AnimePopularMain>

    @GET(value = "OngoingSeries/{page}")
    fun getOnGoingSeries(@Path(value = "page") page: String): Observable<AnimeMain>
}

