package anime.stream.core.ui.menu.bottomsheet

import android.animation.Animator
import android.view.MenuItem
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import anime.stream.core.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetMenu
internal constructor(
    @MenuRes private val menu: Int,
    private val sourceView: View,
    callback: ((item: MenuItem) -> Unit)? = null,
    @LayoutRes private var headerView: Int? = null,
    private val applyZoomEffectToParentView: Boolean = true
) {

    private val dialog by lazy {
        BottomSheetDialog(sourceView.context, R.style.menu_dialog)
    }

    val bottomSheetMenuLayout by lazy {
        BottomSheetMenuLayout(
            sourceView.context,
            null,
            R.style.Theme_AppCompat_DayNight
        )
    }

    init {
        bottomSheetMenuLayout.setupView(callback, dialog, headerView, menu)
    }

    private fun createAndShowDialog(view: View) {
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            ?.setBackgroundResource(android.R.color.transparent)
        dialog.setContentView(bottomSheetMenuLayout)
        dialog.setOnDismissListener {
            zoomIn(view) {
                it.dismiss()
            }
        }
        dialog.show()
    }

    fun show() {
        zoomOut(sourceView) {
            createAndShowDialog(sourceView)
        }
    }

    private fun zoomIn(view: View, callback: () -> Unit) {
        if (applyZoomEffectToParentView.not()) {
            callback()
            return
        }
        view.animate().alpha(1f).scaleX(1f).scaleY(1f).apply {
            duration = 100
        }.onAnimationFinish(callback).start()
    }

    private fun zoomOut(view: View, callback: () -> Unit) {
        if (applyZoomEffectToParentView.not()) {
            callback()
            return
        }
        view.animate().alpha(0.8f).scaleX(0.96f).scaleY(0.96f).apply {
            duration = 100
        }.onAnimationFinish(callback).start()
    }
}

fun ViewPropertyAnimator.onAnimationFinish(callback: () -> Unit): ViewPropertyAnimator {
    setListener(
        object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                callback()
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        }
    )
    return this
}
