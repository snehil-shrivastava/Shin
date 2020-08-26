package anime.stream.home.manga.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import anime.stream.core.services.MangaNetworkService
import anime.stream.core.services.models.manga.Collection
import anime.stream.home.manga.room.CollectionEntity
import anime.stream.home.manga.room.MangaCollectionDao
import anime.stream.home.manga.room.MangaCollectionDatabase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@ExperimentalPagingApi
class MangaRemoteMediator @Inject constructor(
    private val db: MangaCollectionDatabase,
    private val service: MangaNetworkService
) : RxRemoteMediator<Int, CollectionEntity>() {

    private val mcDao: MangaCollectionDao = db.mangaCollectionDao()

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, CollectionEntity>
    ): Single<MediatorResult> {
        return service.getMangaTitles()
            .subscribeOn(Schedulers.io())
            .map {
                db.runInTransaction {
                    if (loadType == LoadType.REFRESH) {
                        mcDao.deleteAll()
                    }
                    val collectionList = collectionEntityList(it)
                    mcDao.insertAll(collectionList)
                }
                return@map MediatorResult.Success(true)
            }.onErrorResumeNext { e ->
                if (e is Exception) {
                    return@onErrorResumeNext Single.just(MediatorResult.Error(e))
                }
                Single.error(e)
            }
    }

    private fun collectionEntityList(it: Collection): ArrayList<CollectionEntity> {
        val collectionList = ArrayList<CollectionEntity>()
        for (item in it.getCollectionIds()) {
            val name = it.getCollectionName(item)
            val titles = it.getList(item)
            val collectionEntity = CollectionEntity(name, titles)
            collectionList.add(collectionEntity)
        }
        return collectionList
    }
}