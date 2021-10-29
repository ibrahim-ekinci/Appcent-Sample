package com.gloorystudio.appcent_sample.ui.game_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloorystudio.appcent_sample.data.local.db.entity.GameFavoriteEntity
import com.gloorystudio.appcent_sample.data.models.GameDetailEntry
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import com.gloorystudio.appcent_sample.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    var gameData = MutableLiveData<GameDetailEntry>()
    var loadError = MutableLiveData("")
    var isLoading = MutableLiveData(false)

    fun fetchGameData(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getGameInfo(id)) {
                is Resource.Success -> {
                    result.data?.let { resultGame ->
                        gameData.value = GameDetailEntry(
                            resultGame.id,
                            resultGame.name,
                            resultGame.background_image,
                            resultGame.rating,
                            resultGame.released,
                            resultGame.metacritic,
                            resultGame.description_raw
                        )
                    }

                    loadError.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun getFavoriteGame(id: Int, isLiked: (Boolean)->Unit) {
        viewModelScope.launch {
           isLiked.invoke(repository.isGameFavoriteToDb(id))
        }
    }

    fun addGameFavorite(){
        gameData.value?.let { gameDetailEntry ->
            viewModelScope.launch {
                repository.addGameFavoriteToDb(
                    GameFavoriteEntity(
                        gameDetailEntry.id,
                        gameDetailEntry.name,
                        gameDetailEntry.image,
                        gameDetailEntry.rating,
                        gameDetailEntry.released,
                        gameDetailEntry.metaCriticRate,
                        gameDetailEntry.description
                    )
                )
            }
        }
    }

    fun removeFavoriteGame(id: Int){
        viewModelScope.launch {
            repository.deleteGameFavoriteToDb(id)
        }
    }
}