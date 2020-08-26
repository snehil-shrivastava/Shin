package anime.stream.home.manga.paging

/*
class MangaAdapter : PagingDataAdapter<CollectionEntity, MangaViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val root: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_manga_fragment, parent, false)
        return MangaViewHolder(root, this.mangaAdapterController)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<CollectionEntity>() {
            override fun areItemsTheSame(
                oldItem: CollectionEntity,
                newItem: CollectionEntity
            ): Boolean =
                oldItem.collectionName == newItem.collectionName

            override fun areContentsTheSame(
                oldItem: CollectionEntity,
                newItem: CollectionEntity
            ): Boolean =
                oldItem == newItem
        }
    }
}*/
