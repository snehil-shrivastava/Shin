package anime.stream.favourites.service.impl

import anime.stream.core.services.FavouriteService
import anime.stream.core.services.FavouriteType
import anime.stream.favourites.room.MangaDatabase
import anime.stream.favourites.room.MangaFavourites
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FavouriteServiceImpl @Inject constructor(private val mangaDB: MangaDatabase) : FavouriteService {
    override fun addToFavourites(type: FavouriteType, id: String, title: String, cover: String, rating: String) {
        when (type) {
            FavouriteType.ANIME -> throw UnsupportedOperationException()
            FavouriteType.MANGA -> mangaDB.mangaFavouritesDao()
                .insert(MangaFavourites(id, title, cover, rating))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onComplete() {
                        Timber.d("Successfully added Manga to favourites!")
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e, "Failed to add manga")
                    }
                })
            else -> throw IllegalArgumentException("type argument can only be a Type.ANIME or TYPE.Manga")
        }
    }

    override fun removeFromFavourites(type: FavouriteType, id: String) {
        when (type) {
            FavouriteType.ANIME -> throw UnsupportedOperationException()
            FavouriteType.MANGA -> mangaDB.mangaFavouritesDao().deleteById(id)
            else -> throw IllegalArgumentException("type argument can only be a Type.ANIME or TYPE.Manga")
        }
    }

    override fun isAlreadyAdded(type: FavouriteType, id: String, callback: (Boolean) -> Unit) {
        when (type) {
            FavouriteType.ANIME -> throw UnsupportedOperationException()
            FavouriteType.MANGA -> mangaDB.mangaFavouritesDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback(true)
                }, {
                    callback(false)
                })
            else -> throw IllegalArgumentException("type argument can only be a Type.ANIME or TYPE.Manga")
        }
    }

}