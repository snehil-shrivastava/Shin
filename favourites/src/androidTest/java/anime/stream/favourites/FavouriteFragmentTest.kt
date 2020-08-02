package anime.stream.favourites

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavouriteFragmentTest {

    @Before
    fun init() {

    }

    @After
    fun close() {
    }

    @Test
    fun testEventFragment() {


        val scenario = launchFragmentInContainer<FavouritesFragment>(
            Bundle.EMPTY,
            R.style.Theme_AppCompat_DayNight,
            Lifecycle.State.RESUMED,
            null
        )
        scenario.moveToState(Lifecycle.State.CREATED)

        onView(withId(R.id.radio_btn_manga))
            .perform(click())
    }

}

