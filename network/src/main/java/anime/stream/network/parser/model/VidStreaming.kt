package anime.stream.network.parser.model

import anime.stream.core.services.models.anime.Stream
import anime.stream.network.animekisa.AnimeKisaConstants.URL_REGEX
import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.annotation.Selector
import java.util.regex.Pattern

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class VidStreaming : Stream {

    @Selector("body > div.wrapper > div")
    private lateinit var javaScript: Element

    private val pattern = Pattern.compile("(file: ')$URL_REGEX")

    fun url(): String {
        val script = javaScript.getElementsByTag("script")[0].data()
        val matcher = pattern.matcher(script)
        return if (matcher.find())
            matcher.group(2)
        else ""
    }

    override fun getLinks(): List<ILink> {
        val links = ArrayList<ILink>()
        links.add(
            ILink("1080", url(), false)
        )
        return links
    }
}
