package com.gloorystudio.appcent_sample.ui.game_fav_list

import androidx.lifecycle.ViewModel
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import javax.inject.Inject

class GameFavoriteListViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

}