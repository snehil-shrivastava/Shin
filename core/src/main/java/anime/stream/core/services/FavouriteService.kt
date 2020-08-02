package anime.stream.core.services

interface FavouriteService {

    fun addToFavourites(type: FavouriteType, id: String, title: String, cover: String, rating: String)

    fun removeFromFavourites(type: FavouriteType, id: String)

    fun isAlreadyAdded(type: FavouriteType, id: String, callback: (Boolean) -> Unit)

}