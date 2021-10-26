package com.gloorystudio.appcent_sample.ui.gamelist

import com.gloorystudio.appcent_sample.base.BaseViewModel
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import javax.inject.Inject

class GameListViewModel @Inject constructor(
    private val repository: GameRepository
): BaseViewModel() {

}