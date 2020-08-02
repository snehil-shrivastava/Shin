package anime.stream.favourites.repository

import anime.stream.favourites.room.MangaDatabase
import anime.stream.favourites.room.MangaFavourites
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MangaFavouritesRepository @Inject constructor(
    private val mangaDatabase: MangaDatabase
) {
    fun getAllMangaFav(): Flowable<List<MangaFavourites>> {
        return mangaDatabase.mangaFavouritesDao().getAll().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun searchMangaFav(query: String): Flowable<List<MangaFavourites>> {
        return mangaDatabase.mangaFavouritesDao().search(query).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun removeFromMangaFavourite(id: String): Completable {
        return mangaDatabase.mangaFavouritesDao().deleteById(id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun fetchDetails() {
        // TODO : Using network calls
    }
}