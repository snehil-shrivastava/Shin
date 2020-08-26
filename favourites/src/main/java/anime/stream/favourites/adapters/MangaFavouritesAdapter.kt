package anime.stream.favourites.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import anime.stream.favourites.R
import anime.stream.favourites.room.MangaFavourites

class MangaFavouritesAdapter(
    private val mangaAdapterController: MangaAdapterController
) : PagingDataAdapter<MangaFavourites, MangaViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_manga_fragment, parent, false)
        return MangaViewHolder(root, this.mangaAdapterController)
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