package anime.stream.shin.base.animegogo
import anime.stream.libnetwork.anime.animegogo.AnimeGogoProvider
import anime.stream.libnetwork.anime.animegogo.Constants.BaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AnimeGogoRetrofitProvider {


    fun getApiInstance(): AnimeGogoProvider {
        val retrofit= Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(AnimeGogoProvider::class.java)

    }

}