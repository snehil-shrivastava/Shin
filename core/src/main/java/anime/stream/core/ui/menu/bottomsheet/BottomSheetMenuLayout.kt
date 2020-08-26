package anime.stream.core.ui.menu.bottomsheet

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.view.menu.MenuAdapter
import androidx.appcompat.view.menu.MenuBuilder
import anime.stream.core.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_menu.view.*

class BottomSheetMenuLayout internal constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int
) :
    LinearLayout(context, attrs, defStyleAttr) {

    private val inflater by lazy {
        context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
    }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_VERTICAL
        setBackgroundResource(R.drawable.background_sheet)
    }

    @SuppressLint("RestrictedApi", "PrivateResource")
    fun setupView(
        callback: ((menu: MenuItem) -> Unit)?,
        dialog: BottomSheetDialog,
        @LayoutRes headerView: Int?,
        @MenuRes menuRes: Int
    ) {
        if (headerView != null) {
            inflater?.inflate(headerView, this, true)
        }
        inflater?.inflate(R.layout.bottom_sheet_menu, this, true)

        val mMenu = MenuBuilder(context)
        MenuInflater(this.context).inflate(menuRes, mMenu)
        val adapter = MenuAdapter(mMenu, inflater, false, R.layout.abc_popup_menu_item_layout)
        menuView.numColumns = 1
        menuView.adapter = adapter
        if (callback != null) {
            menuView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                dialog.dismiss()
                callback(adapter.getItem(position))
            }
        }
    }

}