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
class NetworkModuleProduction {

    @Provides
    @Singleton
    fun jspoonConverterFactory(): JspoonConverterFactory = JspoonConverterFactory.create()

    @Provides
    @Singleton
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    @Named("pOkHttp")
    fun okHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    @Named("pMangaDex")
    fun getMangaProvider(jspoon: JspoonConverterFactory, gson: GsonConverterFactory, @Named("pOkHttp") client: OkHttpClient): MangaDexProvider {
        return Retrofit.Builder().client(client)
            .baseUrl(MangaDexConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(jspoon)
            .addConverterFactory(gson)
            .build().create(MangaDexProvider::class.java)
    }

}