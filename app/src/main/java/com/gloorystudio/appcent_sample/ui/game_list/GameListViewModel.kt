package com.gloorystudio.appcent_sample.ui.game_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloorystudio.appcent_sample.data.remote.models.GameListEntry
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import com.gloorystudio.appcent_sample.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    val repository: GameRepository
) : ViewModel() {

    private var curPage = 0

    var gameList = MutableLiveData<List<GameListEntry>>(listOf())
    var loadError = MutableLiveData("")
    var isLoading = MutableLiveData(false)
    var endReached = MutableLiveData(false)

    init {
        loadGameList()
    }

    fun loadGameList() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getGameList()) {
                is Resource.Success -> {
                    endReached.value = result.data!!.count == 0
                    val gameListEntry = result.data!!.results.mapIndexed { _, entry ->
                        GameListEntry(
                            entry.id,
                            entry.name,
                            entry.background_image,
                            entry.rating,
                            entry.released
                        )
                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    gameList.value = gameList.value?.plus(gameListEntry)
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}