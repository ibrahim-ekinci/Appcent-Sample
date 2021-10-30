package com.gloorystudio.appcent_sample.ui.game_fav_list

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameFavoriteListBinding
import com.gloorystudio.appcent_sample.ui.adapters.GameListFavoriteAdapter
import com.gloorystudio.appcent_sample.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFavoriteListFragment :
    BaseFragment<FragmentGameFavoriteListBinding>(R.layout.fragment_game_favorite_list) {

    private val viewModel: GameFavoriteListViewModel by viewModels()
    private val gameListFavoriteAdapter = GameListFavoriteAdapter(arrayListOf())

    override fun initUi() {
        binding.rvGameList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGameList.adapter = gameListFavoriteAdapter
        gameListFavoriteAdapter.onClickItem { game, view ->
            GameFavoriteListFragmentDirections.actionGameFavoriteListFragmentToGameDetailFragment(
                game.id
            ).navigate(view)
        }
    }

    override fun observeData() {
        viewModel.gameList.observe(viewLifecycleOwner, { gameList ->
            binding.tvNotFound.visibility = if (gameList.isEmpty()) View.VISIBLE else View.GONE
            gameListFavoriteAdapter.updateGameList(gameList)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteGameList()
    }
}