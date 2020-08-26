package anime.stream.core.services

import anime.stream.core.services.models.manga.Chapter
import anime.stream.core.services.models.manga.Collection
import anime.stream.core.services.models.manga.Manga
import io.reactivex.Observable
import io.reactivex.Single

interface MangaNetworkService {

    fun getMangaTitles(): Single<out Collection>

    fun searchManga(query: String, page: Int): Observable<out Collection>

    fun fetchMangaDetails(mangaId: String): Observable<out Manga>

    fun fetchChapterDetails(chapterId: String): Observable<out Chapter>
}
