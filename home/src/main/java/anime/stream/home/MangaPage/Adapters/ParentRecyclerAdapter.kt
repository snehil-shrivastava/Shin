package anime.stream.shin.Nested

import android.content.Context
import android.content.LocusId
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import anime.stream.core.services.models.manga.Collection
import anime.stream.home.R
import anime.stream.network.manga.mangadex.models.MangaDexTitles
import kotlinx.android.synthetic.main.root_manga_item.view.*


class ParentRecyclerAdapter (val collection:Collection,
    private val listener:Context,
    val id: Int
): RecyclerView.Adapter<ParentRecyclerAdapter.ParentViewHolder>(),ChildRecyclerAdpter.OnItemClickListener {



    inner class ParentViewHolder(val view: View): RecyclerView.ViewHolder(view){

        init {


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {

        return ParentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.root_manga_item,parent,false)

        )
    }

    override fun getItemCount()=5


    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.view.rootItemTitle.text=collection.getCollectionName(id)

        holder.view.childRecyclerView.layoutManager=LinearLayoutManager(listener,LinearLayoutManager.HORIZONTAL,false)
        holder.view.childRecyclerView.adapter=ChildRecyclerAdpter(collection.getList(id) as ArrayList<MangaDexTitles>,this)

    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented for child recyclerview")
    }


}