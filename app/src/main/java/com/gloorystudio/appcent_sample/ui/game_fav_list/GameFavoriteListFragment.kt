package com.gloorystudio.appcent_sample.ui.game_fav_list

import androidx.fragment.app.viewModels
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameFavoriteListBinding

class GameFavoriteListFragment :
    BaseFragment<FragmentGameFavoriteListBinding>(R.layout.fragment_game_favorite_list) {

    private val viewModel: GameFavoriteListViewModel by viewModels()

    override fun initUi() {

    }

    override fun observeData() {


    }
}