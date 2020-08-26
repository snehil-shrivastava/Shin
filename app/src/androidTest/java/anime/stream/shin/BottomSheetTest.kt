package anime.stream.shin


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BottomSheetTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun bottomSheetTest() {
        val bubble = onView(
            allOf(
                withId(R.id.menu_nav_bar_favourites),
                childAtPosition(
                    allOf(
                        withId(R.id.chipNavBar),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bubble.perform(click())

        val bubble2 = onView(
            allOf(
                withId(R.id.menu_nav_bar_favourites),
                childAtPosition(
                    allOf(
                        withId(R.id.chipNavBar),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bubble2.perform(click())

        val appCompatButton = onView(
            allOf(
                withId(R.id.btn_add), withText("Anime"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.core.widget.NestedScrollView")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatRadioButton = onView(
            allOf(
                withId(R.id.radio_btn_manga), withText("Manga"),
                childAtPosition(
                    allOf(
                        withId(R.id.radioGroup),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.mangaList),
                childAtPosition(
                    withClassName(`is`("androidx.core.widget.NestedScrollView")),
                    0
                )
            )
        )

        runBlocking { delay(5000) }
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, longClick()))

        val appCompatTextView = onView(
            allOf(
                withId(R.id.title), withText("Remove"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val imageView = onView(
            allOf(
                withId(R.id.cover),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(doesNotExist())


        val viewGroup = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.mangaList),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup.check(doesNotExist())
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
