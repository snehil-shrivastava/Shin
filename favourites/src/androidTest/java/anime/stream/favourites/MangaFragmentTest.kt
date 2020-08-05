package anime.stream.favourites

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MangaFragmentTest {

    companion object {
        @JvmStatic
        val scenario = launchFragmentInContainer<MangaFragment>(
            null, R.style.Theme_AppCompat_DayNight, Lifecycle.State.INITIALIZED, null
        )
    }

    @Before
    fun init() {
        scenario.onFragment {
        }
    }

    @Test
    fun onViewCreated() {
        scenario.moveToState(Lifecycle.State.CREATED)
    }

    @Test
    fun onDestroy() {
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }

}