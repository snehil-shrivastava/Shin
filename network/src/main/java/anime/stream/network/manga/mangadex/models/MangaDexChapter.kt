package anime.stream.network.manga.mangadex.models


import anime.stream.core.services.models.Chapter
import com.google.gson.annotations.SerializedName

data class MangaDexChapter(
    @SerializedName("chapter")
    val chapter: String,
    @SerializedName("comments")
    val comments: Any,
    @SerializedName("external")
    val `external`: String,
    @SerializedName("group_id")
    val groupId: Int,
    @SerializedName("group_id_2")
    val groupId2: Int,
    @SerializedName("group_id_3")
    val groupId3: Int,
    @SerializedName("group_name")
    val groupName: String,
    @SerializedName("group_name_2")
    val groupName2: Any,
    @SerializedName("group_name_3")
    val groupName3: Any,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lang_code")
    val langCode: String,
    @SerializedName("lang_name")
    val langName: String,
    @SerializedName("long_strip")
    val longStrip: Int,
    @SerializedName("manga_id")
    val mangaId: Int,
    @SerializedName("page_array")
    val pageArray: List<Any>,
    @SerializedName("server")
    val server: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("timestamp")
    val timestamp: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("volume")
    val volume: String
) : Chapter