package anime.stream.home.manga.viewmodel

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import anime.stream.home.manga.paging.MangaRemoteMediator
import anime.stream.home.manga.room.CollectionEntity
import anime.stream.home.manga.room.MangaCollectionDatabase
import kotlinx.coroutines.flow.Flow

class MangaCollectionRepository @ExperimentalPagingApi constructor(
    private val mangaDatabase: MangaCollectionDatabase,
    private val mediator: MangaRemoteMediator,
    private val pagerConfig: PagingConfig
) {
    fun getAllMangaFav(): Flow<PagingData<CollectionEntity>> {
        return Pager(pagerConfig, null, mediator) {
            mangaDatabase.mangaCollectionDao().getAllPaged()
        }.flow
    }
}