package com.mwp.games4clues.model

data class GameLevel(
    val route: String,
    val level: Int,
    var isUnlocked: Boolean,
    val name: String = level.toString(),
)
