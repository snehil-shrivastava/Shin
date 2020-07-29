package anime.stream.libnetwork.di

import anime.stream.libnetwork.manga.mangadex.MangaDexConstants
import anime.stream.libnetwork.manga.mangadex.MangaDexProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
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
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newReq = chain
                .request()
                .newBuilder()
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36")
                .addHeader(
                    "cookie", " __ddg1=MR8e23ZgLAkz9Sl1AfqH; __ddg2=snqbTazvR17GZjtt; mangadex_session=cad55894-bda8-4dd7-9185-1abaa61c" +
                            "d4c8; mangadex_rememberme_token=aadb8b6fd78d05c0c32fea09f2d71b61addf9ff6c224c099319386becd43705d"
                )
                .build()
            chain.proceed(newReq)
        }.build()
    }

    @Provides
    @Singleton
    fun getMangaProvider(
        jspoon: JspoonConverterFactory, gson: GsonConverterFactory,
        @Named("dOkHttp") okHttpClient: OkHttpClient
    ): MangaDexProvider {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(MangaDexConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(jspoon)
            .addConverterFactory(gson)
            .build().create(MangaDexProvider::class.java)
    }
}