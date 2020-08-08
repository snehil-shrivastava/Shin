package anime.stream.core.services.models.anime

/**
 * @param name -> name of the server like vidcdn, mp4upload, hydrax
 * @param embeddedUrl -> url of the embedded player
 */
data class Server(val name: String, val embeddedUrl: String)
