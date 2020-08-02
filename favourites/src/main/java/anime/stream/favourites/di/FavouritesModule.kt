package anime.stream.favourites.di

import android.content.Context
import anime.stream.favourites.room.MangaDatabase
import dagger.Module
import dagger.Provides

@Module
object FavouritesModule {

    @FavouriteScope
    @Provides
    fun getDatabase(app: Context) = MangaDatabase.getInstance(app)

}