package anime.stream.shin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedMainActivity {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext


    }
}
