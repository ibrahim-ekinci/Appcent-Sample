package com.gloorystudio.appcent_sample.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameFavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "metaCriticRate")
    val metaCriticRate: Int,
    @ColumnInfo(name = "description")
    val description : String
)
