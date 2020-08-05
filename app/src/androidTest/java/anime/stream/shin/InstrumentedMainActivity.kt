package anime.stream.shin

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class InstrumentedMainActivity {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testBasicNavigation() {
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
        Thread.sleep(2500)
        basicNavigation()
    }

    private fun basicNavigation() {
        val bubble = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.menu_nav_bar_favourites),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.chipNavBar),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bubble.perform(ViewActions.click())


        val linearLayout = Espresso.onView(
            Matchers.allOf(
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(RecyclerView::class.java),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        linearLayout.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(500)
        linearLayout.perform(ViewActions.swipeLeft())
        Thread.sleep(500)
        val recyclerView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.mangaList),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(FrameLayout::class.java),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        recyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        recyclerView.perform(ViewActions.swipeRight())


        val bubble2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.menu_nav_bar_home),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.chipNavBar),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bubble2.perform(ViewActions.click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
