package anime.stream.core.services.models.manga

interface Collection {

    fun getCollectionIds(): Array<Int>

    fun getCollectionName(id: Int): String

    fun getList(id: Int): List<Titles>

    fun preProcess()
}
