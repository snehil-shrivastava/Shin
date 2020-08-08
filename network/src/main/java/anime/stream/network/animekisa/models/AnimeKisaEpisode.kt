package anime.stream.network.animekisa.models

import anime.stream.core.services.models.anime.Server
import anime.stream.network.animekisa.AnimeKisaConstants.URL_REGEX
import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.annotation.Selector
import java.util.regex.Pattern

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AnimeKisaEpisode {

    @Selector("#body")
    private lateinit var javaScript: Element

    private val pattern: Pattern =
        Pattern.compile("(var\\s*(VidStreaming|VidHLS|Fembed|Fembed2|MP4Upload|Hydrax|Cloud9)\\s*=\\s*\")$URL_REGEX")

    fun getServerList(): List<Server> {
        val list = ArrayList<Server>()
        val jsBlock = javaScript.getElementsByTag("script")[2].data()
        val m = pattern.matcher(jsBlock)
        while (m.find())
            list.add(Server(m.group(2), m.group(3)))
        return list
    }

}
