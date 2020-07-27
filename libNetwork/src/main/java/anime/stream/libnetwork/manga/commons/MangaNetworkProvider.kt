package anime.stream.libnetwork.manga.commons

import anime.stream.libnetwork.manga.commons.models.Chapter
import anime.stream.libnetwork.manga.commons.models.Collection
import anime.stream.libnetwork.manga.commons.models.Manga
import anime.stream.libnetwork.manga.commons.models.Titles
import io.reactivex.Observable
import retrofit2.http.Header


interface MangaNetworkProvider {

    fun getMangaTitles(): Observable<out Collection>

    fun searchManga(query: String, page: Int): Observable<out Collection>

    fun fetchMangaDetails(mangaId: String): Observable<out Manga>

    fun fetchChapterDetails(chapterId: String): Observable<out Chapter>
}
