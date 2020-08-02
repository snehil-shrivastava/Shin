package anime.stream.favourites.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MangaFavourites::class], version = 1, exportSchema = true)
abstract class MangaDatabase : RoomDatabase() {

    abstract fun mangaFavouritesDao(): MangaFavouritesDao

    companion object {

        @Volatile
        private var INSTANCE: MangaDatabase? = null

        fun getInstance(context: Context): MangaDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context)
                        .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MangaDatabase::class.java, "favourites_manga.db"
            )
                .build()
    }
}