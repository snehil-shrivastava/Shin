package anime.stream.libnetwork.anime.commons

import anime.stream.libnetwork.anime.commons.model.AnimeDataModel
import anime.stream.libnetwork.anime.commons.model.AnimeEpisodeDataModel
import io.reactivex.Observable
import retrofit2.Call

/*
* An
* */

interface AnimeNetworkProvider {

    fun getRecentlyAddedSeries(): Observable<List<AnimeDataModel>>

    fun searchAnime(name: String): Observable<AnimeDataModel>

    fun getOnGoingSeries(): Observable<List<AnimeDataModel>>

    fun getPopularAnime(): Observable<List<AnimeDataModel>>

    fun getAnimeEpisodes(id: String): Observable<AnimeEpisodeDataModel>

    fun getRecentReleaseEpisodes(): Observable<List<AnimeEpisodeDataModel>>

}

