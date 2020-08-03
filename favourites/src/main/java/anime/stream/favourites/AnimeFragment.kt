package anime.stream.favourites

import androidx.fragment.app.Fragment
import anime.stream.favourites.di.injector

class AnimeFragment : Fragment(R.layout.fragment_anime) {

    private val mangaNetworkService by lazy { injector.networkService }
    private val favouriteService by lazy { injector.serviceImpl }

    companion object {

        private var mFragment: AnimeFragment? = null

        @JvmStatic
        fun getInstance(): AnimeFragment {
            synchronized(this) {
                if (mFragment == null) {
                    mFragment = AnimeFragment()
                }
                return mFragment ?: AnimeFragment()
            }
        }
    }
}