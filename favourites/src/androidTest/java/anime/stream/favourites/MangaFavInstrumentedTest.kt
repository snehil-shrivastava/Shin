package anime.stream.favourites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import anime.stream.favourites.room.MangaDatabase
import anime.stream.favourites.room.MangaFavourites
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MangaFavInstrumentedTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MangaDatabase

    @Before
    fun initFragment() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MangaDatabase::class.java
        )
            // allowing main thread queries, just for testing
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeFragment() {
        database.close()
    }

    @Test
    fun getMangaFavWhenDbIsEmpty() {
        database.mangaFavouritesDao().getById("123")
            .test()
            .assertNoValues()
    }

    @Test
    fun insertAndGetMangaFav() {
        // When inserting a new MangaFav in the data source
        database.mangaFavouritesDao().insert(MANGA).blockingAwait()

        // When subscribing to the emissions of the MangaFav
        database.mangaFavouritesDao().getById(MANGA.id)
            .test()
            // assertValue asserts that there was only one emission of the MangaFav
            .assertValue { it.id == MANGA.id && it.title == MANGA.title }
    }

    @Test
    fun updateAndGetMangaFav() {
        // Given that we have a MangaFav in the data source
        database.mangaFavouritesDao().insert(MANGA).blockingAwait()

        // When we are updating the name of the MangaFav
        val updatedTitle = MangaFavourites(MANGA.id, "new Manga", MANGA.cover, MANGA.rating)
        database.mangaFavouritesDao().insert(updatedTitle).blockingAwait()

        // When subscribing to the emissions of the MangaFav
        database.mangaFavouritesDao().getById(MANGA.id)
            .test()
            // assertValue asserts that there was only one emission of the MangaFav
            .assertValue { it.id == updatedTitle.id && it.title == updatedTitle.title }
    }

    @Test
    fun deleteAndGetMangaFav() {
        // Given that we have a MangaFav in the data source
        database.mangaFavouritesDao().insert(MANGA).blockingAwait()

        //When we are deleting all MangaFav
        database.mangaFavouritesDao().deleteAll()
        // When subscribing to the emissions of the MangaFav
        database.mangaFavouritesDao().getById(MANGA.id)
            .test()
            // check that there's no MangaFav emitted
            .assertNoValues()
    }


    companion object {
        private val MANGA = MangaFavourites("92", "title", "hello", "world")
    }
}

