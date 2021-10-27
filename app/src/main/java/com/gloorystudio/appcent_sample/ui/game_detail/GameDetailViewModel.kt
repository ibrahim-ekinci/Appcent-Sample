package com.gloorystudio.appcent_sample.ui.game_detail

import androidx.lifecycle.ViewModel
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel@Inject constructor(
    val repository: GameRepository
) : ViewModel() {

}