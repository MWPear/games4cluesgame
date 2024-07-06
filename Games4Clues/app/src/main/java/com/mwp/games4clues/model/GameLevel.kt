package com.mwp.games4clues.model

data class GameLevel(
    val name: String,
    val route: String,
    val level: Int,
    var isUnlocked: Boolean
)
