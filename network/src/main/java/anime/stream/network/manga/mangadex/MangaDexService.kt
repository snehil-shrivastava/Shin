package anime.stream.network.manga.mangadex

import anime.stream.core.services.MangaNetworkService
import anime.stream.network.manga.mangadex.models.MangaDexChapter
import anime.stream.network.manga.mangadex.models.MangaDexCollection
import anime.stream.network.manga.mangadex.models.MangaDexManga
import anime.stream.network.manga.mangadex.models.MangaDexSearchCollection
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaDexService : MangaNetworkService {

    @GET("/")
    override fun getMangaTitles(): Observable<MangaDexCollection>

    @GET("search")
    override fun searchManga(
        @Query("title") query: String,
        @Query("p") page: Int
    ): Observable<MangaDexSearchCollection>

    @GET("api/manga/{mangaId}")
    override fun fetchMangaDetails(@Path("mangaId") mangaId: String): Observable<MangaDexManga>

    @GET("api/chapter/{chapterId}")
    override fun fetchChapterDetails(@Path("chapterId") chapterId: String): Observable<MangaDexChapter>

}
