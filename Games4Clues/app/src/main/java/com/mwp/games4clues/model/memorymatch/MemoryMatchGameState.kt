package com.mwp.games4clues.model.memorymatch

import com.mwp.games4clues.model.GameState

data class MemoryMatchGameState(
    val board: List<List<Card>> = emptyList(),
    val firstSelected: Pair<Int, Int>? = null,
    val secondSelected: Pair<Int, Int>? = null,
    val matchesFound: Int = 0,
    val gameState: GameState = GameState.Ongoing
)