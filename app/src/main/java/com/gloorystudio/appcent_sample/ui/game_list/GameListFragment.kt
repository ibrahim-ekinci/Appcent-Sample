package com.gloorystudio.appcent_sample.ui.game_list

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameListFragment : BaseFragment<FragmentGameListBinding>(R.layout.fragment_game_list) {

    private val viewModel: GameListViewModel by viewModels()

    override fun initUI() {

    }

    override fun observeData() {
        viewModel.gameList.observe(viewLifecycleOwner,{gameList ->
            if (gameList.isNotEmpty()) {
              //TODO update adapter
            }
        })
    }
}