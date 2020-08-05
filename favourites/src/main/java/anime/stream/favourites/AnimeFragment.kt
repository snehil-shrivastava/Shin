package anime.stream.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import anime.stream.core.services.FavouriteType
import anime.stream.core.services.models.manga.setId
import anime.stream.favourites.di.injector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_anime.*
import javax.inject.Singleton

class AnimeFragment : Fragment(R.layout.fragment_anime) {

    private val mangaNetworkService by lazy { injector.networkService }
    private val favouriteService by lazy { injector.serviceImpl }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add.setOnClickListener {
            val id = (Math.random() * 1000).toInt().toString()
            mangaNetworkService.fetchMangaDetails(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ manga ->
                    manga?.setId(id)?.let {
                        favouriteService.addToFavourites(
                            FavouriteType.MANGA,
                            it.id, it.title(), it.coverUrl(), it.rating()
                        )
                    }
                }, {})
        }
    }

    @Singleton
    companion object {
        @Singleton
        @JvmStatic
        @Synchronized
        fun getInstance() = AnimeFragment()
    }
}