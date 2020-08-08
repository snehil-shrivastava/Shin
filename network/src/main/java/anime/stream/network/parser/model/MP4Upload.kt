package anime.stream.network.parser.model

import anime.stream.core.services.models.anime.Stream
import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.annotation.Selector
import java.util.regex.Pattern

/**
 * MP4 upload requires vpn to work
 * */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MP4Upload : Stream {

    @Selector("body")
    private lateinit var javaScript: Element

    private val mPattern = Pattern.compile("\\'((\\|.*)*)\\'\\.")

    private fun url(): String {
        val text = javaScript.getElementsByTag("script")[1].data()
        val matcher = mPattern.matcher(text)
        return if (matcher.find()) {
            generateUrlByCodes(matcher.group(2))
        } else " "
    }

    private fun generateUrlByCodes(group: String): String {
        val l = group.split("|")
        return "${l[3]}://${l[27]}.${l[2]}.${l[1]}:${l[56]}/d/${l[55]}/${l[12]}.${l[25]}"
    }

    /**
     * @throws RuntimeException socket time out and array out of bounds
     * */
    override fun getLinks(): List<ILink> {
        val links = ArrayList<ILink>()
        links.add(
            ILink("1080", url(), false)
        )
        return links
    }
}
