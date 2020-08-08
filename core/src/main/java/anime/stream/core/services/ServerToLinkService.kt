package anime.stream.core.services

import anime.stream.core.services.models.anime.Server
import anime.stream.core.services.models.anime.Stream
import io.reactivex.Observable

/**
 * Supported url
 * 1. http://vidstreaming.io/load.php
 */
interface ServerToLinkService {
    /**
     * @return Observable of type stream
     * if cannot parse the embedded link than returned
     * stream will have only one link with embedded set
     * to true and the url will be the server url
     * */
    fun getVideoLinksFromServer(server: Server): Observable<out Stream>
}

/*
* Streams -> Folders
* Links   -> Files
* */
