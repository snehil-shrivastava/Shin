package anime.stream.favourites.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import anime.stream.favourites.room.MangaDatabase
import anime.stream.favourites.room.MangaFavourites
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MangaFavouritesRepository @Inject constructor(
    private val mangaDatabase: MangaDatabase,
    private val pagerConfig: PagingConfig
) {


    fun getAllMangaFav(): Flowable<PagingData<MangaFavourites>> {
        return Pager(pagerConfig) {
            mangaDatabase.mangaFavouritesDao().getAll()
        }.flowable
    }

    fun searchMangaFav(query: String): Flowable<PagingData<MangaFavourites>> {
        return Pager(pagerConfig) {
            mangaDatabase.mangaFavouritesDao().search(query)
        }.flowable
    }

    fun removeFromMangaFavourite(id: String): Completable {
        return mangaDatabase.mangaFavouritesDao().deleteById(id)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun fetchDetails() {
        // TODO : Using network calls
    }
}