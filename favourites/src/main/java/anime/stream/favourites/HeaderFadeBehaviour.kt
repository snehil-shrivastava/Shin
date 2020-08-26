package anime.stream.favourites

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_favourites.view.*
import kotlin.math.roundToInt
import kotlin.properties.Delegates


/**
 *   What i want to achieve here is simple translation of views
 *   Upward scroll
 *   1 -> When scroll up first if the search bar is open than consume the scroll to close search
 *          bar.
 *   2 -> On further scroll drag and continue
 */
/**
 * Simple scrolling behavior that monitors nested events in the scrolling
 * container to implement a quick hide/show for the attached view.
 */
class QuickHideBehavior : CoordinatorLayout.Behavior<View> {
    /* Tracking direction of user motion */
    private var mScrollingDirection = 0

    /* Tracking last threshold crossed */
    private var mScrollTrigger = 0

    /* Accumulated scroll distance */
    private var mScrollDistance = 0

    /* Distance threshold to trigger animation */
    private var mScrollThreshold = 0
    private var mAnimator: ObjectAnimator? = null

    //Required to instantiate as a default behavior
    constructor()

    //Required to attach behavior via XML
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        val a = context.theme
            .obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
        //Use half the standard action bar height
        mScrollThreshold = a.getDimensionPixelSize(0, 0) / 2
        a.recycle()
    }

    //Called before a nested scroll event. Return true to declare interest
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View, directTargetChild: View, target: View,
        nestedScrollAxes: Int
    ): Boolean {
        //We have to declare interest in the scroll to receive further events
        return nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    //Called before the scrolling child consumes the event
    // We can steal all/part of the event by filling in the consumed array
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View, target: View,
        dx: Int, dy: Int,
        consumed: IntArray
    ) {
        //Determine direction changes here
        if (dy > 0 && mScrollingDirection != DIRECTION_UP) {
            mScrollingDirection = DIRECTION_UP
            mScrollDistance = 0
        } else if (dy < 0 && mScrollingDirection != DIRECTION_DOWN) {
            mScrollingDirection = DIRECTION_DOWN
            mScrollDistance = 0
        }
    }

    //Called after the scrolling child consumes the event, with amount consumed
    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View, target: View,
        dxConsumed: Int, dyConsumed: Int,
        dxUnconsumed: Int, dyUnconsumed: Int
    ) {
        //Consumed distance is the actual distance traveled by the scrolling view
        mScrollDistance += dyConsumed
        if (mScrollDistance > mScrollThreshold
            && mScrollTrigger != DIRECTION_UP
        ) {
            //Hide the target view
            mScrollTrigger = DIRECTION_UP
            restartAnimator(child, getTargetHideValue(coordinatorLayout, child))
        } else if (mScrollDistance < -mScrollThreshold
            && mScrollTrigger != DIRECTION_DOWN
        ) {
            //Return the target view
            mScrollTrigger = DIRECTION_DOWN
            restartAnimator(child, 0f)
        }
    }

    //Called after the scrolling child handles the fling
    override fun onNestedFling(
        coordinatorLayout: CoordinatorLayout,
        child: View, target: View,
        velocityX: Float, velocityY: Float,
        consumed: Boolean
    ): Boolean {
        //We only care when the target view is already handling the fling
        if (consumed) {
            if (velocityY > 0 && mScrollTrigger != DIRECTION_UP) {
                mScrollTrigger = DIRECTION_UP
                restartAnimator(child, getTargetHideValue(coordinatorLayout, child))
            } else if (velocityY < 0 && mScrollTrigger != DIRECTION_DOWN) {
                mScrollTrigger = DIRECTION_DOWN
                restartAnimator(child, 0f)
            }
        }
        return false
    }

    /* Helper Methods */ //Helper to trigger hide/show animation
    private fun restartAnimator(target: View, value: Float) {
        if (mAnimator != null) {
            mAnimator!!.cancel()
            mAnimator = null
        }
        mAnimator = ObjectAnimator
            .ofFloat(target, View.TRANSLATION_Y, value)
            .setDuration(250)
        mAnimator!!.start()
    }

    private fun getTargetHideValue(parent: ViewGroup, target: View): Float {
        return if (target is AppBarLayout) {
            (-target.getHeight()).toFloat()
        } else if (target is FloatingActionButton) {
            (parent.height - target.getTop()).toFloat()
        } else {
            target.height.toFloat()
        }
    }

    companion object {
        private const val DIRECTION_UP = 1
        private const val DIRECTION_DOWN = -1
    }
}

class HeaderFadeBehaviour(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var isSearchBarCollapsed by Delegates.notNull<Boolean>()

    private var mInitialPos = 0f

    private var mVelocityTracker: VelocityTracker? = null


    private val tempRect1 = Rect()
    private val tempRect2 = Rect()

    private var verticalLayoutGap = 0
    private val overlayTop = 0

    private var y = 0f
    private var totalScroll = 0

    override fun onMeasureChild(
        parent: CoordinatorLayout,
        child: View,
        parentWidthMeasureSpec: Int,
        widthUsed: Int,
        parentHeightMeasureSpec: Int,
        heightUsed: Int
    ): Boolean {
        val header: View = parent.findViewById(R.id.radioGroup)
        val searchBar: View = parent.findViewById(R.id.searchBar)

        val availableHeight: Int = parent.height
        val childLpHeight = child.layoutParams.height

        var height: Int = availableHeight + getScrollRange(header)
        val headerHeight: Int = header.measuredHeight
        if (shouldHeaderOverlapScrollingChild()) {
            child.translationY = -headerHeight.toFloat()
        } else {
            height -= headerHeight
        }
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            height,
            if (childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT)
                View.MeasureSpec.EXACTLY else View.MeasureSpec.AT_MOST
        )

        mInitialPos = header.y + header.height
        searchBar.translationY = mInitialPos - searchBar.measuredHeight
        isSearchBarCollapsed = true

        parent.onMeasureChild(
            child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed
        )
        return true
    }


    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: View,
        layoutDirection: Int
    ): Boolean {
        val header = parent.radioGroup
        val lp =
            child.layoutParams as CoordinatorLayout.LayoutParams
        val available: Rect = tempRect1
        available.set(
            parent.paddingLeft + lp.leftMargin,
            header.bottom + lp.topMargin,
            parent.width - parent.paddingRight - lp.rightMargin,
            parent.height + header.bottom - parent.paddingBottom - lp.bottomMargin
        )

        val out: Rect = tempRect2
        GravityCompat.apply(
            resolveGravity(lp.gravity),
            child.measuredWidth,
            child.measuredHeight,
            available,
            out,
            layoutDirection
        )

        val overlap: Int = overlayTop

        child.layout(out.left, out.top - overlap, out.right, out.bottom - overlap)
        verticalLayoutGap = out.top - header.bottom
        return true
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        event: MotionEvent
    ): Boolean {
        var returnFlag = false
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mVelocityTracker?.clear()
                mVelocityTracker = mVelocityTracker ?: VelocityTracker.obtain()
                mVelocityTracker?.addMovement(event)
                val pointerId: Int = event.getPointerId(event.actionIndex)
                y = event.getY(pointerId)
            }
            MotionEvent.ACTION_MOVE -> {
                mVelocityTracker?.apply {
                    val pointerId: Int = event.getPointerId(event.actionIndex)
                    addMovement(event)
                    computeCurrentVelocity(1000)
                    val search = parent.searchBar
                    val vy = getYVelocity(pointerId)
                    val dy = event.getY(pointerId) - y
                    if (dy > 0 && isSearchBarCollapsed) {
                        totalScroll += dy.roundToInt()
                        if (totalScroll > 64) {
                            totalScroll = 0
                            isSearchBarCollapsed = false
                            ObjectAnimator.ofFloat(
                                search,
                                "translationY",
                                mInitialPos + search.height
                            ).start()
                        } else {
                            search.translationY = dy.toFloat()
                        }
                        returnFlag = true
                    }
                    y = event.getY(pointerId)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker?.recycle()
                mVelocityTracker = null
            }
        }
        return returnFlag
    }


    private fun resolveGravity(gravity: Int): Int =
        if (gravity == Gravity.NO_GRAVITY) GravityCompat.START or Gravity.TOP else gravity

    private fun shouldHeaderOverlapScrollingChild(): Boolean = false

    private fun getScrollRange(v: View): Int = v.measuredHeight
}