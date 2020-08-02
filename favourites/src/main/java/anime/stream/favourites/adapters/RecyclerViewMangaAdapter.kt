package anime.stream.favourites.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import anime.stream.favourites.R
import anime.stream.favourites.room.MangaFavourites
import kotlinx.android.synthetic.main.item_manga_fragment.view.*

class RecyclerViewMangaAdapter(private var mangaList: List<MangaFavourites>, private val removeCallback: (id: String) -> Unit) :
    RecyclerView.Adapter<RecyclerViewMangaAdapter.MangaViewHolder>() {

    inner class MangaViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(mangaFavourites: MangaFavourites) {
            view.thumbnail.setImageURI(mangaFavourites.cover)
            view.title.text = mangaFavourites.title
            view.rating.rating = mangaFavourites.rating.toFloat()
            view.like.setOnClickListener {
                removeCallback(mangaFavourites.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val root: View = LayoutInflater.from(parent.context).inflate(R.layout.item_manga_fragment, parent, false)
        return MangaViewHolder(root)
    }

    override fun getItemCount(): Int {
        return mangaList.size
    }


    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(mangaList[position])
    }

    fun update(it: List<MangaFavourites>) {
        mangaList = it
        notifyDataSetChanged()
    }
}