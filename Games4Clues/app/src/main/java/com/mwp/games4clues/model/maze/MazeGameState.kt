package com.mwp.games4clues.model.maze

import com.mwp.games4clues.model.GameState

data class MazeGameState(
    val maze: List<List<Int>>,
    val playerPosition: Pair<Int, Int> = Pair(0, 0),
    val gameState: GameState = GameState.Ongoing
)