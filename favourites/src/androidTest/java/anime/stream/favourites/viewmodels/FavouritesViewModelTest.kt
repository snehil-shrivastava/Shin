package anime.stream.favourites.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import anime.stream.favourites.room.MangaFavourites
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FavouritesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    lateinit var observer: Observer<List<MangaFavourites>>

    @Before
    fun setup() {
    }

    companion object;

    @Test
    fun fetchUser_ShouldReturnUser() {
        // TODO CREATE A GOOD VIEW MODEL TEST PATTERN
    }
}
