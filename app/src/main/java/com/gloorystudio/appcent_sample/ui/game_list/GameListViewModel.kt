package com.gloorystudio.appcent_sample.ui.game_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloorystudio.appcent_sample.data.models.GameListEntry
import com.gloorystudio.appcent_sample.data.repository.GameRepository
import com.gloorystudio.appcent_sample.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    var gameList = MutableLiveData<List<GameListEntry>>(listOf())
    var loadError = MutableLiveData("")
    var isLoading = MutableLiveData(false)

    init {
        fetchGameList()
    }

    fun fetchGameList() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getGameList()) {
                is Resource.Success -> {
                    result.data?.let {
                        val gameListEntry = result.data.results.mapIndexed { _, entry ->
                            GameListEntry(
                                entry.id,
                                entry.name,
                                entry.background_image,
                                entry.rating,
                                entry.released
                            )
                        }
                        loadError.value = ""
                        isLoading.value = false
                        gameList.value = gameList.value?.plus(gameListEntry)
                    }
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}