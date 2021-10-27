package com.gloorystudio.appcent_sample.ui.game_list

import android.os.Bundle
import android.view.View
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameListBinding


class GameListFragment : BaseFragment<FragmentGameListBinding>(R.layout.fragment_game_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tv.text
    }
}