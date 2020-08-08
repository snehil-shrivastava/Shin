package anime.stream.network.parser

import anime.stream.network.parser.model.Fembed
import anime.stream.network.parser.model.MP4Upload
import anime.stream.network.parser.model.VidStreaming
import io.reactivex.Observable
import retrofit2.http.*

interface ParserService {
    @GET
    fun getVidStreamingUrl(
        @Url url: String
    ): Observable<VidStreaming>

    @POST
    @FormUrlEncoded
    fun getFembedUrl(@Field("d") rawData: String, @Url url: String): Observable<Fembed>

    @GET
    fun getMP4UploadUrl(@Url url: String): Observable<MP4Upload>
}
