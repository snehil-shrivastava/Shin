package anime.stream.network.anime.commons

import anime.stream.network.anime.commons.model.AnimeDataModel
import anime.stream.network.anime.commons.model.AnimeEpisodeDataModel
import io.reactivex.Observable

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

