package anime.stream.home.manga.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import anime.stream.core.services.models.manga.Titles

@Entity(tableName = "mangaCollection")
data class CollectionEntity(
    @PrimaryKey
    @ColumnInfo(name = "collectionName")
    val collectionName: String,
    @ColumnInfo(name = "titles")
    val titles: List<Titles>
)