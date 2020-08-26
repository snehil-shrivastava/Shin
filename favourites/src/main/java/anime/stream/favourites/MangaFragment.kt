package anime.stream.favourites

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavOptions
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import anime.stream.core.di.viewModel
import anime.stream.core.interfaces.Searchable
import anime.stream.core.utils.IntentUtility
import anime.stream.favourites.adapters.MangaAdapterController
import anime.stream.favourites.adapters.MangaFavouritesAdapter
import anime.stream.favourites.di.injector
import anime.stream.favourites.viewmodels.MangaViewModel
import kotlinx.android.synthetic.main.fragment_manga.*
import kotlinx.android.synthetic.main.fragment_manga.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Singleton


class MangaFragment : Fragment(R.layout.fragment_manga), Searchable {

    private val viewModel by viewModel {
        injector.viewModel.create(this).create(MangaViewModel::class.java)
    }

    private val adapter = MangaFavouritesAdapter(object : MangaAdapterController {
        override fun removeMangaFromFavourites(mangaId: String) {
            viewModel.removeMangaItem(mangaId)
        }

        override fun viewManga(mangaId: String) {
            val navigator = ActivityNavigator(this@MangaFragment.requireContext())
            val destination = navigator.createDestination()
            destination.intent = IntentUtility.getMangaIntent(mangaId)
            val options = NavOptions.Builder().build()
            navigator.navigate(destination, null, options, null)
        }

        override fun shareManga(mangaUrl: String) {
            TODO("Not yet implemented")
        }

    })

    @ExperimentalCoroutinesApi
    override fun onViewCreated(root: View, savedInstanceState: Bundle?) {
        super.onViewCreated(root, savedInstanceState)
        root.mangaList.adapter = adapter
        root.mangaList.layoutManager = LinearLayoutManager(root.context)
        setupMangaList()
    }


    @ExperimentalCoroutinesApi
    private fun setupMangaList() {
        lifecycleScope.launch {
            viewModel.pagingData.cachedIn(lifecycleScope).collectLatest {
                adapter.submitData(it)
            }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                progressBar.isVisible = loadStates.refresh is Loading
                if (loadStates.refresh is LoadState.NotLoading) {
                    if (adapter.itemCount == 0)
                        hideRecyclerView()
                    else showRecyclerView()
                }
            }
        }
    }

    private fun hideRecyclerView() {
        mangaList.visibility = View.GONE
        noManga.visibility = View.VISIBLE
    }

    private fun showRecyclerView() {
        mangaList.visibility = View.VISIBLE
        noManga.visibility = View.GONE
    }

    override fun onDestroy() {
        mangaList?.layoutManager = null
        super.onDestroy()
    }

    @Singleton
    companion object {
        @Singleton
        @Synchronized
        @JvmStatic
        fun getInstance() = MangaFragment()
    }

    override fun search(query: String) {
        viewModel.searchManga(query)
    }

}