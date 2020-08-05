package anime.stream.favourites

/**
 *  @author snehil
 * */

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import anime.stream.core.di.viewModel
import anime.stream.favourites.adapters.RecyclerViewMangaAdapter
import anime.stream.favourites.di.injector
import anime.stream.favourites.viewmodels.MangaViewModel
import kotlinx.android.synthetic.main.fragment_manga.*
import kotlinx.android.synthetic.main.fragment_manga.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Singleton


class MangaFragment : Fragment(R.layout.fragment_manga) {

    private val viewModel by viewModel {
        injector.viewModel.create(this).create(MangaViewModel::class.java)
    }

    private val adapter = RecyclerViewMangaAdapter()

    override fun onViewCreated(root: View, savedInstanceState: Bundle?) {
        super.onViewCreated(root, savedInstanceState)
        root.mangaList.adapter = adapter
        root.mangaList.layoutManager = LinearLayoutManager(root.context)
        setupMangaList()
    }


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
}