package anime.stream.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import anime.stream.favourites.databinding.FragmentFavouritesBinding
import anime.stream.favourites.di.injector
import anime.stream.favourites.util.Constants.ANIME
import anime.stream.favourites.util.Constants.MANGA
import anime.stream.favourites.viewmodels.FavouritesViewModel
import kotlinx.android.synthetic.main.fragment_favourites.*
import javax.inject.Singleton


class FavouritesFragment : Fragment() {

    private val vmFactory by lazy { injector.viewModel }
//    private val mAdapter by lazy {PagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)}

    override fun onCreateView(
        inflater: LayoutInflater,
        cont: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFavouritesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favourites, cont, false
        )
        binding.fav = vmFactory.create(this).create(FavouritesViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container.adapter = PagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
    }

    @Singleton
    companion object {
        @Singleton
        @Synchronized
        @JvmStatic
        fun getInstance() = FavouritesFragment()
    }

    private inner class PagerAdapter(fa: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fa, lifecycle) {
        private val pageCount: Int = 2

        private var animeFragment: AnimeFragment? = AnimeFragment.getInstance()
        private var mangaFragment: MangaFragment? = MangaFragment.getInstance()

        override fun getItemCount(): Int = pageCount

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                ANIME -> animeFragment!!
                MANGA -> mangaFragment!!
                else -> animeFragment!!
            }
        }

        fun dispose() {
            animeFragment = null
            mangaFragment = null
        }

    }
}