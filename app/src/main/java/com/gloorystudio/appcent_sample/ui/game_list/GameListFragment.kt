package com.gloorystudio.appcent_sample.ui.game_list

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameListBinding
import com.gloorystudio.appcent_sample.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameListFragment : BaseFragment<FragmentGameListBinding>(R.layout.fragment_game_list) {

    private val viewModel: GameListViewModel by viewModels()
    private val gameListAdapter = GameListAdapter(arrayListOf())

    override fun initUI() {
        binding.rvGameList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGameList.adapter = gameListAdapter
        gameListAdapter.onClickItem { game, view ->
            GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(game.id)
                .navigate(view)
        }

        binding.svGameSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 2 || newText.isEmpty())
                    gameListAdapter.filter.filter(newText)
                else gameListAdapter.clearFilter()
                return false
            }
        })
    }

    override fun observeData() {
        viewModel.gameList.observe(viewLifecycleOwner, { gameList ->
            if (gameList.isNotEmpty()) {
                gameListAdapter.updateGameList(gameList)
            }
        })
    }
}