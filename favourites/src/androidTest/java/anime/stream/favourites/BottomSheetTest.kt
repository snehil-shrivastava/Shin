package anime.stream.favourites

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import anime.stream.core.ui.menu.bottomsheet.BottomSheetMenuBuilder
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BottomSheetTest {

    companion object {
        lateinit var source: View
    }

    @Before
    fun initialize() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        source = View(appContext)
    }

    @Test(expected = IllegalStateException::class)
    fun builderWithoutMenu() {
        // Context of the app under test.
        BottomSheetMenuBuilder()
            .eventSourceView(source).build()
    }

    @Test(expected = IllegalStateException::class)
    fun builderWithoutSourceView() {
        // Context of the app under test.
        BottomSheetMenuBuilder().menu(10).build()
    }

    @Test
    fun checkIfBuilderWorks() {
        BottomSheetMenuBuilder()
            .menu(R.menu.menu_item_manga)
            .eventSourceView(source)
            .attachHeaderView(R.layout.bottom_sheet_menu_header)
            .onMenuItemSelected {
                Assert.assertTrue("bottom sheet menu is not created", it.isVisible)
                it.isVisible
            }
            .build()
    }
}
