package anime.stream.favourites.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import anime.stream.favourites.room.MangaDatabase
import anime.stream.favourites.room.MangaFavourites
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaFavouritesRepository @Inject constructor(
    private val mangaDatabase: MangaDatabase,
    private val pagerConfig: PagingConfig
) {


    fun getAllMangaFav(): Flow<PagingData<MangaFavourites>> {
        return Pager(pagerConfig) {
            mangaDatabase.mangaFavouritesDao().getAll()
        }.flow
    }

    fun searchMangaFav(query: String): Flow<PagingData<MangaFavourites>> {
        return Pager(pagerConfig) {
            mangaDatabase.mangaFavouritesDao().search(query)
        }.flow
    }

    fun removeFromMangaFavourite(id: String): Completable {
        return mangaDatabase.mangaFavouritesDao().deleteById(id)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun fetchDetails() {
        // TODO : Using network calls
    }
}