package anime.stream.network.di

import anime.stream.network.animekisa.AnimeKisaConstants
import anime.stream.network.animekisa.AnimeKisaService
import anime.stream.network.manga.mangadex.MangaDexConstants
import anime.stream.network.manga.mangadex.MangaDexService
import anime.stream.network.parser.ParserService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModuleDebug {

    @Provides
    @Singleton
    @Named("dOkHttp")
    fun client(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newReq = chain
                        .request()
                        .newBuilder()
                        .addHeader(
                            "user-agent",
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36"
                        )
                        .addHeader(
                            "cookie",
                            " __ddg1=MR8e23ZgLAkz9Sl1AfqH; __ddg2=snqbTazvR17GZjtt; mangadex_session=cad55894-bda8-4dd7-9185-1abaa61c" +
                                    "d4c8; mangadex_rememberme_token=aadb8b6fd78d05c0c32fea09f2d71b61addf9ff6c224c099319386becd43705d"
                        )
                        .build()
                    return chain.proceed(newReq)
                }

            })
            .build()
    }

    @Provides
    @Singleton
    fun getMangaProvider(
        jspoon: JspoonConverterFactory,
        gson: GsonConverterFactory,
        @Named("dOkHttp") okHttpClient: OkHttpClient,
        rxJava: RxJava2CallAdapterFactory
    ): MangaDexService {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(MangaDexConstants.BASE_URL)
            .addCallAdapterFactory(rxJava)
            .addConverterFactory(jspoon)
            .addConverterFactory(gson)
            .build().create(MangaDexService::class.java)
    }

    @Provides
    @Singleton
    fun getAnimeKisaProvider(
        jspoon: JspoonConverterFactory,
        @Named("pOkHttp") okHttpClient: OkHttpClient,
        rxJava: RxJava2CallAdapterFactory
    ): AnimeKisaService {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(AnimeKisaConstants.BASE_URL)
            .addCallAdapterFactory(rxJava)
            .addConverterFactory(jspoon)
            .build().create(AnimeKisaService::class.java)
    }

    @Provides
    @Singleton
    fun parserService(
        jspoon: JspoonConverterFactory,
        gson: GsonConverterFactory,
        @Named("pOkHttp") okHttpClient: OkHttpClient,
        rxJava: RxJava2CallAdapterFactory
    ): ParserService {
        return Retrofit.Builder().client(okHttpClient)
            .addCallAdapterFactory(rxJava)
            .baseUrl("http://g.co/")
            .addConverterFactory(jspoon)
            .addConverterFactory(gson)
            .build().create(ParserService::class.java)
    }
}
