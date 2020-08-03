package anime.stream.network.anime.animegogo

import anime.stream.network.anime.animegogo.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AnimeGogoRetrofitProvider {


    fun getApiInstance(): AnimeGogoProvider {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(AnimeGogoProvider::class.java)

    }

}