package com.mwp.games4clues.model.tictactoe

import com.mwp.games4clues.model.GameState

data class TicTacToeGameState(
    val board: List<List<String>> = List(3) { List(3) { "" } },
    val currentPlayer: String = "X",
    val gameState: GameState = GameState.Ongoing,
    val winner: String? = null
)