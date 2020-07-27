package anime.stream.libnetwork.manga.mangadex.models


import anime.stream.libnetwork.manga.commons.models.Manga
import com.google.gson.annotations.SerializedName

data class MangaDexManga(
    @SerializedName("chapter")
    val chapter: Map<Int, MapValue>,
    @SerializedName("group")
    val group: Map<Int, MapValueGRP>,
    @SerializedName("manga")
    val manga: Manga,
    @SerializedName("status")
    val status: String
) : Manga {
    data class MapValue(
        @SerializedName("chapter")
        val chapter: String,
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
        @SerializedName("lang_code")
        val langCode: String,
        @SerializedName("timestamp")
        val timestamp: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("volume")
        val volume: String
    )

    data class MapValueGRP(
        @SerializedName("group_name")
        val groupName: String
    )

    data class Manga(
        @SerializedName("alt_names")
        val altNames: List<Any>,
        @SerializedName("artist")
        val artist: String,
        @SerializedName("author")
        val author: String,
        @SerializedName("cover_url")
        val coverUrl: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("genres")
        val genres: List<Any>,
        @SerializedName("hentai")
        val hentai: Int,
        @SerializedName("lang_flag")
        val langFlag: String,
        @SerializedName("lang_name")
        val langName: String,
        @SerializedName("last_chapter")
        val lastChapter: String,
        @SerializedName("links")
        val links: Links,
        @SerializedName("rating")
        val rating: Rating,
        @SerializedName("status")
        val status: Int,
        @SerializedName("title")
        val title: String
    ) {
        data class Links(
            @SerializedName("al")
            val al: String,
            @SerializedName("ap")
            val ap: String,
            @SerializedName("engtl")
            val engtl: String,
            @SerializedName("kt")
            val kt: String,
            @SerializedName("mal")
            val mal: String,
            @SerializedName("mu")
            val mu: String,
            @SerializedName("raw")
            val raw: String
        )

        data class Rating(
            @SerializedName("bayesian")
            val bayesian: String,
            @SerializedName("mean")
            val mean: String,
            @SerializedName("users")
            val users: String
        )
    }
}