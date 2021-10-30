package com.gloorystudio.appcent_sample.data.remote

import com.gloorystudio.appcent_sample.data.remote.responses.game.Game
import com.gloorystudio.appcent_sample.data.remote.responses.gamelist.GameList
import com.gloorystudio.appcent_sample.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameApi {

    @GET("games")
    suspend fun getGameList(
        @Query("key") key:String = API_KEY
    ): GameList

    @GET("games/{gameId}")
    suspend fun getGameInfo(
        @Path("gameId") gameId: Int,
        @Query("key") key:String = API_KEY
    ): Game
}