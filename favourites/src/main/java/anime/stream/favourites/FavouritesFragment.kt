package anime.stream.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import anime.stream.favourites.databinding.FragmentFavouritesBinding
import anime.stream.favourites.di.injector
import anime.stream.favourites.util.Constants.ANIME
import anime.stream.favourites.util.Constants.MANGA
import anime.stream.favourites.viewmodels.FavouritesViewModel
import kotlinx.android.synthetic.main.fragment_favourites.*


class FavouritesFragment : Fragment() {

    private val vmFactory by lazy { injector.viewModel }


    override fun onCreateView(inflater: LayoutInflater, cont: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentFavouritesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favourites, cont, false
        )
        binding.fav = vmFactory.create(this).create(FavouritesViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container.adapter = PagerAdapter(this)
    }

    companion object {
        @JvmStatic
        private var mFragment: FavouritesFragment? = null

        @JvmStatic
        @SuppressWarnings("unused")
        fun getInstance(): FavouritesFragment {
            if (mFragment == null) {
                mFragment = FavouritesFragment()
            }
            return mFragment ?: FavouritesFragment()
        }
    }

    private inner class PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        private val pageCount: Int = 2

        override fun getItemCount(): Int = pageCount

        override fun createFragment(position: Int): Fragment =
            when (position) {
                ANIME -> AnimeFragment.getInstance()
                MANGA -> MangaFragment.getInstance()
                else -> AnimeFragment.getInstance()
            }
    }
}