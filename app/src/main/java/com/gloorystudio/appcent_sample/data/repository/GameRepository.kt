package com.gloorystudio.appcent_sample.data.repository

import android.content.res.Resources
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.data.remote.GameApi
import com.gloorystudio.appcent_sample.data.remote.responses.game.Game
import com.gloorystudio.appcent_sample.data.remote.responses.gamelist.GameList
import com.gloorystudio.appcent_sample.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class GameRepository @Inject constructor(
    private val api: GameApi
) {
    suspend fun getGameList(page: Int = 1): Resource<GameList> {
        val response = try {
            api.getGameList(page)
        } catch (e: Exception) {
            return Resource.Error(
                Resources.getSystem().getString(R.string.an_unknown_error_occurred)
            )
        }
        return Resource.Success(response)
    }

    suspend fun getGameInfo(gameId: Int): Resource<Game> {
        val response = try {
            api.getGameInfo(gameId)
        } catch (e: java.lang.Exception) {
            return Resource.Error(
                Resources.getSystem().getString(R.string.an_unknown_error_occurred)
            )
        }
        return Resource.Success(response)
    }
}
