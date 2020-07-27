package anime.stream.libnetwork.manga.mangadex

import anime.stream.libnetwork.manga.commons.MangaNetworkProvider
import anime.stream.libnetwork.manga.commons.models.*
import anime.stream.libnetwork.manga.mangadex.models.*
import io.reactivex.Observable
import okhttp3.Cookie
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaDexProvider : MangaNetworkProvider {

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

