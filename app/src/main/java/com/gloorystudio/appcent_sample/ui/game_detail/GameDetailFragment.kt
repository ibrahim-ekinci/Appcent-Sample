package com.gloorystudio.appcent_sample.ui.game_detail


import androidx.fragment.app.viewModels
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentGameDetailBinding
import com.gloorystudio.appcent_sample.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailFragment : BaseFragment<FragmentGameDetailBinding>(R.layout.fragment_game_detail) {

    private val viewModel: GameDetailViewModel by viewModels()
    private var isLiked = false

    override fun initUi() {
        arguments?.let {
            val myArgs = GameDetailFragmentArgs.fromBundle(it)
            viewModel.fetchGameData(myArgs.gameId)
            viewModel.getFavoriteGame(myArgs.gameId) { isLiked ->
                this.isLiked = isLiked
                if (isLiked) {
                    binding.ivLike.setImageResource(R.drawable.ic_unlike)
                } else {
                    binding.ivLike.setImageResource(R.drawable.ic_like)
                }
            }
            binding.ivLike.setOnClickListener {
                isLiked = if (isLiked) {
                    viewModel.removeFavoriteGame(myArgs.gameId)
                    binding.ivLike.setImageResource(R.drawable.ic_like)
                    false
                } else {
                    viewModel.addGameFavorite()
                    binding.ivLike.setImageResource(R.drawable.ic_unlike)
                    true
                }
            }
        }
    }

    override fun observeData() {
        viewModel.gameData.observe(viewLifecycleOwner, { gameData ->
            gameData?.let {
                binding.game = gameData
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            MainActivity.instance.showLoading(isLoading)
        })
    }
}