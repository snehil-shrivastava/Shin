package stream.libnetwork.manga.mangadex

import anime.stream.libnetwork.anime.animegogo.AnimeGogoProvider
import anime.stream.libnetwork.anime.animegogo.Constants.BaseUrl
import anime.stream.shin.base.commons.model.AnimeEpisodeMain
import anime.stream.shin.base.commons.model.AnimeMain
import anime.stream.shin.base.commons.model.AnimePopularMain
import anime.stream.shin.base.commons.model.AnimeSearchMain
import io.reactivex.Observable
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import junit.framework.Assert.*
import org.junit.Assert
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sun.rmi.runtime.Log
import java.io.IOException

class AnimeNetworkTest {

/*
    fun getretrofit(): AnimeGogoProvider {
        val retrofit= Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AnimeGogoProvider::class.java)
        return retrofit

    }

    @Test
    fun ApiTest(){
        val service=getretrofit()
        val call:Call<AnimeEpisodeMain>
        call=service.getRecentReleaseEpisodes("1")
        try {
            val res:Response<AnimeEpisodeMain> =call.execute()
            val authResponse=res.body()
            Assert.assertTrue(res.isSuccessful && authResponse!=null)
            val title:String= authResponse?.anime?.get(0)?.title.toString()
            assertTrue(title,!title.isBlank())



        }
        catch (e:IOException){

            e.printStackTrace();

        }
    }
    @Test
    fun ApiTest2(){
        val service=getretrofit()
        val call:Call<AnimeSearchMain>
        call=service.getSearchAnime("demon%20slayer")
        try {
            val res:Response<AnimeSearchMain> =call.execute()
            val authResponse=res.body()
                Assert.assertTrue(res.isSuccessful && authResponse!=null)
            if (authResponse != null) {
                assertEquals("Kimetsu no Yaiba",authResponse.search[0].title)
            }
        }
        catch (e:IOException){
            e.printStackTrace();
        }
    }
    @Test
    fun ApiTest3(){
        val service=getretrofit()
        val call:Call<AnimeMain>
        call=service.getRecentlyAddedSeries()
        try {
            val res:Response<AnimeMain> =call.execute()
            val authResponse=res.body()
            Assert.assertTrue(res.isSuccessful && authResponse!=null)
            if (authResponse != null) {
                val title:String= authResponse?.anime?.get(0)?.title.toString()
                assertTrue(title,!title.isBlank())

            }
        }
        catch (e:IOException){
            e.printStackTrace();
        }
    }
    @Test
    fun ApiTest4(){
        val service=getretrofit()
        val call:Call<AnimePopularMain>
        call=service.getPopularAnime("1")
        try {
            val res:Response<AnimePopularMain> =call.execute()
            val authResponse=res.body()
            Assert.assertTrue(res.isSuccessful)
            if (authResponse != null) {
                val title:String= authResponse?.popular?.get(0)?.title.toString()
                assertTrue(title,!title.isBlank())

            }
        }
        catch (e:IOException){
            e.printStackTrace();
        }
    }

    @Test
    fun ApiTest5(){
        val service=getretrofit()
        val call:Call<AnimeEpisodeMain>
        call=service.getAnimeEpisodes("kimetsu-no-yaiba-episode-3")
        try {
            val res:Response<AnimeEpisodeMain> =call.execute()
            val authResponse=res.body()
            Assert.assertTrue(res.isSuccessful && authResponse!=null)
            if (authResponse != null ) {

                val img= authResponse.anime[0].img

                assertEquals("https://gogocdn.net/cover/kimetsu-no-yaiba.png",img)

            }
        }
        catch (e:IOException){
            e.printStackTrace();
        }
    }*/
}