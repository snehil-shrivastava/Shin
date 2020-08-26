package anime.stream.home.manga.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CollectionEntity::class], version = 1, exportSchema = true)
abstract class MangaCollectionDatabase : RoomDatabase() {

    abstract fun mangaCollectionDao(): MangaCollectionDao

    companion object {

        @Volatile
        private var INSTANCE: MangaCollectionDatabase? = null

        fun getInstance(context: Context): MangaCollectionDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context)
                        .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MangaCollectionDatabase::class.java, "collection_manga.db"
            )
                .build()
    }
}