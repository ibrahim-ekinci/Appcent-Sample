package com.gloorystudio.appcent_sample.data.repository

import com.gloorystudio.appcent_sample.data.local.db.entity.GameFavoriteEntity
import com.gloorystudio.appcent_sample.data.local.db.service.GameDao
import com.gloorystudio.appcent_sample.data.remote.GameApi
import com.gloorystudio.appcent_sample.data.remote.responses.game.Game
import com.gloorystudio.appcent_sample.data.remote.responses.gamelist.GameList
import com.gloorystudio.appcent_sample.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class GameRepository @Inject constructor(
    private val api: GameApi,
    private val dao: GameDao
) {
    suspend fun getGameList(): Resource<GameList> {
        val response = try {
            api.getGameList()
        } catch (e: Exception) {
            return Resource.Error(
                e.message.toString()
            )
        }
        return Resource.Success(response)
    }

    suspend fun getGameInfo(gameId: Int): Resource<Game> {
        val response = try {
            api.getGameInfo(gameId)
        } catch (e: java.lang.Exception) {
            return Resource.Error(
                e.message.toString()
            )
        }
        return Resource.Success(response)
    }

    suspend fun addGameFavoriteToDb(gameFavoriteEntity: GameFavoriteEntity){
       dao.insertGameFavorite(gameFavoriteEntity)
    }

    suspend fun deleteGameFavoriteToDb(id:Int){
        dao.deleteGameFavorite(id)
    }

    suspend fun isGameFavoriteToDb(id: Int): Boolean{
       return dao.getGameFavorite(id)!=null
    }

    suspend fun getAllFavoriteGameToDb(): List<GameFavoriteEntity>? {
        return dao.getAllGameFavorite()
    }
}
