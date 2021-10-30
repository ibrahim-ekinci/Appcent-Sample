package com.gloorystudio.appcent_sample.ui.game_fav_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloorystudio.appcent_sample.data.models.GameListEntry
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameFavoriteListViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    var gameList = MutableLiveData<ArrayList<GameListEntry>>(arrayListOf())

    init {
        getFavoriteGameList()
    }

    fun getFavoriteGameList() {
        viewModelScope.launch {
            repository.getAllFavoriteGameToDb()?.let { gameListEntity ->
                val gameList = arrayListOf<GameListEntry>()
                gameListEntity.forEach { gameFavoriteEntity ->
                    gameList.add(
                        GameListEntry(
                            gameFavoriteEntity.id,
                            gameFavoriteEntity.name,
                            gameFavoriteEntity.image,
                            gameFavoriteEntity.rating,
                            gameFavoriteEntity.released
                        )
                    )
                }
                this@GameFavoriteListViewModel.gameList.value = gameList
            }
        }
    }
}