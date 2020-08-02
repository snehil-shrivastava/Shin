package anime.stream.favourites.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MangaFavouritesDao {


    @Query("SELECT * FROM `Manga Favourites`")
    fun getAll(): Flowable<List<MangaFavourites>>

    @Query("SELECT * FROM `Manga Favourites` WHERE id = :id")
    fun getById(id: String): Flowable<MangaFavourites>


    @Query("SELECT * FROM `Manga Favourites` WHERE title LIKE :query")
    fun search(query: String): Flowable<List<MangaFavourites>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(manga: MangaFavourites): Completable

    @Query("DELETE FROM `Manga Favourites`")
    fun deleteAll()

    @Query("DELETE FROM `Manga Favourites` WHERE id = :id")
    fun deleteById(id: String): Completable

}