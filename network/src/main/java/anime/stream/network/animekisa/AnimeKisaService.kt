package anime.stream.network.animekisa

import anime.stream.core.services.AnimeNetworkService
import anime.stream.network.animekisa.models.AnimeKisaEpisode
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeKisaService : AnimeNetworkService {

    @GET("/{episodeId}")
    fun getEpisode(@Path("episodeId") episodeId: String): Observable<AnimeKisaEpisode>

}
