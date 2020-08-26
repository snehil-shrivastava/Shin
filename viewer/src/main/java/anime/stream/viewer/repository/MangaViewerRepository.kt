package anime.stream.viewer.repository

import anime.stream.core.services.FavouriteService
import anime.stream.core.services.FavouriteType
import anime.stream.core.services.MangaNetworkService
import anime.stream.core.services.models.manga.Manga
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MangaViewerRepository @Inject constructor(
    private val mangaNetworkService: MangaNetworkService,
    private val mangaFavouriteService: FavouriteService
) {
    fun isFavourite(mangaId: String, callback: (Boolean) -> Unit) {
        mangaFavouriteService.isAlreadyAdded(FavouriteType.MANGA, mangaId, callback)
    }

    fun addToFavourite(id: String, title: String, cover: String, rating: String) {
        mangaFavouriteService.addToFavourites(FavouriteType.MANGA, id, title, cover, rating)
    }

    fun removeFromFavourite(mangaId: String) {
        mangaFavouriteService.removeFromFavourites(FavouriteType.MANGA, mangaId)
    }

    fun loadManga(mangaId: String): Observable<out Manga> =
        mangaNetworkService.fetchMangaDetails(mangaId).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(
                Schedulers.io()
            )


}