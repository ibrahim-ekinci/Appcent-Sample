package com.gloorystudio.appcent_sample.data.remote.responses.gamelist

import com.gloorystudio.appcent_sample.data.remote.responses.game.Game

data class GameList(
    val count: Int,
    val description: String,
    val filters: Filters,
    val next: String,
    val nofollow: Boolean,
    val nofollow_collections: List<String>,
    val noindex: Boolean,
    val previous: Any,
    val results: List<Game>,
    val seo_description: String,
    val seo_h1: String,
    val seo_keywords: String,
    val seo_title: String
)