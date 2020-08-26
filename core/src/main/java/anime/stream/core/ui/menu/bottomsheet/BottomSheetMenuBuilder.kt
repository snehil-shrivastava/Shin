package anime.stream.core.ui.menu.bottomsheet

import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes

class BottomSheetMenuBuilder {

    private var callback: ((id: MenuItem) -> Unit)? = null

    private var actionView: View? = null

    @LayoutRes
    private var headerView: Int? = null

    @MenuRes
    private var menu: Int? = null

    fun onMenuItemSelected(callback: (id: MenuItem) -> Unit) = apply { this.callback = callback }

    fun attachHeaderView(@LayoutRes header: Int) = apply { this.headerView = header }

    fun eventSourceView(view: View) = apply { this.actionView = view }

    fun menu(menuRes: Int) = apply { this.menu = menuRes }

    fun build(): BottomSheetMenu {
        if (actionView == null) throw IllegalStateException("Should set event source view using Builder.eventSourceView")
        if (menu == null) throw IllegalStateException("Should set menu using Builder.menu")
        return BottomSheetMenu(menu!!, actionView!!, callback, headerView)
    }

}