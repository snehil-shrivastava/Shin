package anime.stream.home.manga.room

import androidx.paging.PagingSource
import androidx.room.*
import io.reactivex.Completable


@Dao
interface MangaCollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: CollectionEntity): Completable

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<CollectionEntity>): Completable

    @Query(
        """
            SELECT * FROM mangaCollection ORDER BY collectionName ASC
            """
    )

    fun getAllPaged(): PagingSource<Int, CollectionEntity>

    @Query(
        """
        DELETE FROM mangaCollection
    """
    )
    fun deleteAll(): Completable

}
