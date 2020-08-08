package anime.stream.network.parser

import anime.stream.core.services.ServerToLinkService
import anime.stream.core.services.models.anime.Link
import anime.stream.core.services.models.anime.Server
import anime.stream.core.services.models.anime.Stream
import io.reactivex.Observable
import javax.inject.Inject

class IServerToLink @Inject constructor(private val service: ParserService) :
    ServerToLinkService {

    override fun getVideoLinksFromServer(server: Server): Observable<out Stream> {
        val url = server.embeddedUrl
        val def = Observable.fromArray(
            object : Stream {
                override fun getLinks(): List<Link> {
                    return listOf(
                        object : Link {
                            override val quality: String = "Not available"
                            override val url: String = url
                            override val isEmbedded = true
                        }
                    )
                }
            }
        )
        return try {
            when (server.name) {
                "VidStreaming" -> {
                    service.getVidStreamingUrl(url)
                }
                "Fembed" -> {
                    service.getFembedUrl(
                        "",
                        "https://fcdn.stream/api/source/${url.substring(22, url.length)}"
                    )

                }
                "MP4Upload" -> {
                    service.getMP4UploadUrl(url)
                }
                else -> {
                    def
                }
            }
        } catch (e: Exception) {
            def
        }
    }
}
