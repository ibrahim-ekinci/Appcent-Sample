package com.gloorystudio.appcent_sample.ui.game_list

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameListBinding
import com.gloorystudio.appcent_sample.ui.adapters.GameListAdapter
import com.gloorystudio.appcent_sample.ui.adapters.ViewPagerAdapter
import com.gloorystudio.appcent_sample.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameListFragment : BaseFragment<FragmentGameListBinding>(R.layout.fragment_game_list) {

    private val viewModel: GameListViewModel by viewModels()
    private val gameListAdapter = GameListAdapter(arrayListOf())
    private val viewPagerAdapter = ViewPagerAdapter(arrayListOf())

    override fun initUi() {
        binding.rvGameList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGameList.adapter = gameListAdapter
        gameListAdapter.onClickItem { game, view ->
            GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(game.id)
                .navigate(view)
        }

        binding.vpSlider.adapter = viewPagerAdapter
        binding.dotsIndicator.setViewPager(binding.vpSlider)
        viewPagerAdapter.onClickItem { gameListEntry, view ->
            GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(gameListEntry.id)
                .navigate(view)
        }

        binding.svGameSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 2) {
                    gameListAdapter.filter.filter(newText)
                    binding.vpSlider.visibility = View.GONE
                    binding.dotsIndicator.visibility = View.GONE
                } else {
                    gameListAdapter.clearFilter()
                    binding.vpSlider.visibility = View.VISIBLE
                    binding.dotsIndicator.visibility = View.VISIBLE
                }
                return false
            }
        })
    }

    override fun observeData() {
        viewModel.gameList.observe(viewLifecycleOwner, { gameList ->
            if (gameList.isNotEmpty()) {
                gameListAdapter.updateGameList(gameList)
                viewPagerAdapter.updateGameList(gameList.take(3))
            }
        })
    }
}