package anime.stream.favourites.adapters

import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import anime.stream.core.ui.menu.bottomsheet.BottomSheetMenuBuilder
import anime.stream.favourites.R
import anime.stream.favourites.room.MangaFavourites
import kotlinx.android.synthetic.main.bottom_sheet_menu_header.view.*
import kotlinx.android.synthetic.main.item_manga_fragment.view.*
import kotlinx.android.synthetic.main.item_manga_fragment.view.title

class MangaViewHolder(
    private val view: View,
    private val mangaAdapterController: MangaAdapterController
) : RecyclerView.ViewHolder(view) {

    fun bind(mangaFavourites: MangaFavourites) {
        /** rating is in 10 so divide it by 2 as rating must be < 5 */
        val mRating = mangaFavourites.rating.toFloat() / 2

        view.thumbnail.setImageURI(mangaFavourites.cover)
        view.title.text = mangaFavourites.title
        view.rating.rating = mRating
        view.setOnLongClickListener { view ->
            view?.let {
                createAndShowBottomSheetMenu(mangaFavourites, it)
                return@setOnLongClickListener true
            }
            return@setOnLongClickListener false
        }
    }

    private fun createAndShowBottomSheetMenu(
        mangaFavourites: MangaFavourites,
        rootView: View
    ) {
        val bottomSheetMenu = BottomSheetMenuBuilder()
            .attachHeaderView(R.layout.bottom_sheet_menu_header)
            .menu(R.menu.menu_item_manga)
            .onMenuItemSelected(onMenuItemSelected(mangaFavourites))
            .eventSourceView(rootView)
            .build()

        bottomSheetMenu.bottomSheetMenuLayout.apply {
            cover.setImageURI(mangaFavourites.cover)
            title.text = mangaFavourites.title
        }

        bottomSheetMenu.show()
    }

    private fun onMenuItemSelected(
        mangaFavourites: MangaFavourites
    ): (MenuItem) -> Unit = { menuItem ->
        onMenuItemSelected(mangaFavourites, menuItem)
    }

    private fun onMenuItemSelected(
        mangaFavourites: MangaFavourites,
        menuItem: MenuItem
    ): Boolean {
        val id = mangaFavourites.id
        when (menuItem.itemId) {
            R.id.menu_option_remove -> mangaAdapterController.removeMangaFromFavourites(id)
            R.id.menu_option_share -> mangaAdapterController.shareManga(id)
            R.id.menu_option_view -> mangaAdapterController.viewManga(id)
        }
        return true
    }
}