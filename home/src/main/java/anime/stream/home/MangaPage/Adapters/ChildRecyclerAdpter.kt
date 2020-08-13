package anime.stream.shin.Nested

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import anime.stream.home.R


import anime.stream.network.manga.mangadex.models.MangaDexTitles
import kotlinx.android.synthetic.main.child_manga_item.view.*

class ChildRecyclerAdpter (val titles:ArrayList<MangaDexTitles>,
                           private val listener:OnItemClickListener
): RecyclerView.Adapter<ChildRecyclerAdpter.ChildViewHolder>() {



    inner class ChildViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener{

        init {
            view.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            val position=adapterPosition
            listener.onItemClick(position)

        }


    }
    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {

        return ChildViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.child_manga_item,parent,false)

        )
    }

    override fun getItemCount()=titles.size


    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.view.child_textView.text=titles[position].mangaName

        TODO("load manga cover in childimageview")


    }
}