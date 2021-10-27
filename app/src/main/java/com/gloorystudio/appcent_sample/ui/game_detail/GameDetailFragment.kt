package com.gloorystudio.appcent_sample.ui.game_detail


import androidx.fragment.app.viewModels
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailFragment : BaseFragment<FragmentGameDetailBinding>(R.layout.fragment_game_detail) {

    private val viewModel: GameDetailViewModel by viewModels()

    override fun initUI() {

    }

    override fun observeData() {

    }
}