package anime.stream.network.parser.model

import anime.stream.core.services.models.anime.Stream
import com.google.gson.annotations.SerializedName

data class Fembed(
    @SerializedName("data")
    val `data`: List<Data>
) : Stream {

    override fun getLinks(): List<ILink> {
        val links = ArrayList<ILink>()
        for (it in `data`) {
            links.add(
                ILink(
                    it.label,
                    it.file,
                    false
                )
            )
        }
        return links
    }

    data class Data(
        @SerializedName("file")
        val `file`: String,
        @SerializedName("label")
        val label: String,
        @SerializedName("type")
        val type: String
    )

}
