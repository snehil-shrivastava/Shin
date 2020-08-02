package anime.stream.favourites

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import anime.stream.favourites.util.Constants.ANIME
import anime.stream.favourites.util.Constants.MANGA
import anime.stream.favourites.viewmodels.FavouritesViewModel

object BindingAdapters {
    @BindingAdapter("bind:selectAnimePage")
    @JvmStatic
    fun selectViewPagerPage(pagerViewPager: ViewPager2, state: Boolean) {
        pagerViewPager.currentItem = if (state) ANIME else MANGA
    }

    @BindingAdapter("bind:onPageChange")
    @JvmStatic
    fun highlightPageTitle(viewPager: ViewPager2, fav: FavouritesViewModel) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    MANGA -> fav.selectMangaPage()
                    ANIME -> fav.selectAnimePage()
                }
            }
        })
    }

//    @BindingAdapter("bind:imageUrl")
//    @JvmStatic
//    fun imageUrl(draweeView: SimpleDraweeView, url: String) {
//        draweeView.setImageURI(url)
//    }
}