package anime.stream.favourites.adapters

import android.view.*
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import anime.stream.favourites.R
import anime.stream.favourites.room.MangaFavourites
import kotlinx.android.synthetic.main.item_manga_fragment.view.*

class RecyclerViewMangaAdapter :
    PagingDataAdapter<MangaFavourites, RecyclerViewMangaAdapter.MangaViewHolder>(diffCallback) {

    inner class MangaViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnCreateContextMenuListener {
        fun bind(mangaFavourites: MangaFavourites) {
            view.thumbnail.setImageURI(mangaFavourites.cover)
            view.title.text = mangaFavourites.title
            val mRating = mangaFavourites.rating.toFloat() / 2
            view.rating.rating = mRating
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.setHeaderView(v?.thumbnail)
            MenuInflater(v?.context).inflate(R.menu.menu_manga_item, menu)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_manga_fragment, parent, false)
        return MangaViewHolder(root)
    }


    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MangaFavourites>() {
            override fun areItemsTheSame(
                oldItem: MangaFavourites,
                newItem: MangaFavourites
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MangaFavourites,
                newItem: MangaFavourites
            ): Boolean =
                oldItem == newItem
        }
    }
}