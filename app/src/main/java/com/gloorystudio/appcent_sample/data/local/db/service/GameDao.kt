package com.gloorystudio.appcent_sample.data.local.db.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.gloorystudio.appcent_sample.data.local.db.entity.GameFavoriteEntity

@Dao
interface GameDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertGameFavorite(gameFavoriteEntity: GameFavoriteEntity)

    @Query("SELECT * FROM gameFavoriteEntity WHERE id=:id")
    suspend fun getGameFavorite(id: Int): GameFavoriteEntity?

    @Query("DELETE FROM gameFavoriteEntity WHERE id=:id")
    suspend fun deleteGameFavorite(id: Int)

    @Query("SELECT * FROM gameFavoriteEntity")
    suspend fun getAllGameFavorite(): List<GameFavoriteEntity>?
}
