package com.gloorystudio.appcent_sample.ui.game_list

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameListBinding
import com.gloorystudio.appcent_sample.ui.SplashFragmentDirections
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
            GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(game.id).navigate(view)
        }
    }

    override fun observeData() {
        viewModel.gameList.observe(viewLifecycleOwner, { gameList ->
            if (gameList.isNotEmpty()) {
             gameListAdapter.updateGameList(gameList)
            }
        })
    }
}