package anime.stream.favourites.adapters

interface MangaAdapterController {

    fun removeMangaFromFavourites(mangaId: String)

    fun viewManga(mangaId: String)

    fun shareManga(mangaUrl: String)

}