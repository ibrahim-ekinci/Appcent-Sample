package com.gloorystudio.appcent_sample.data.remote.models

data class GameDetailEntry(
    val id: Int,
    val name: String,
    val image: String,
    val rating: Double,
    val released: String,
    val metaCriticRate: Int,
    val description : String
)
