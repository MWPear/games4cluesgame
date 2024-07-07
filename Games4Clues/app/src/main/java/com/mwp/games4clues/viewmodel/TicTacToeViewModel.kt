package com.mwp.games4clues.viewmodel

import androidx.lifecycle.ViewModel
import com.mwp.games4clues.model.GameState
import com.mwp.games4clues.model.TicTacToeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TicTacToeViewModel(level: Int) : ViewModel() {
    private val _state = MutableStateFlow(TicTacToeModel())
    val state: StateFlow<TicTacToeModel> get() = _state

    fun makePlayerMove(row: Int, col: Int) {
        val currentState = _state.value
        if (currentState.board[row][col] != "" || currentState.gameState != GameState.Ongoing) {
            return
        }

        val newBoard = currentState.board.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, cell ->
                if (r == row && c == col) currentState.currentPlayer else cell
            }
        }

        val newGameState = checkGameState(newBoard, currentState.currentPlayer)

        _state.value = currentState.copy(
            board = newBoard,
            currentPlayer = if (currentState.currentPlayer == "X") "O" else "X",
            gameState = newGameState,
            winner = if (newGameState == GameState.Win) currentState.currentPlayer else null
        )
    }

    fun resetGame() {
        _state.value = TicTacToeModel()
    }

    private fun checkGameState(board: List<List<String>>, currentPlayer: String): GameState {
        val winPatterns = listOf(
            listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2)),
            listOf(Pair(1, 0), Pair(1, 1), Pair(1, 2)),
            listOf(Pair(2, 0), Pair(2, 1), Pair(2, 2)),
            listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0)),
            listOf(Pair(0, 1), Pair(1, 1), Pair(2, 1)),
            listOf(Pair(0, 2), Pair(1, 2), Pair(2, 2)),
            listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
            listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0))
        )

        if (winPatterns.any { pattern -> pattern.all { (r, c) -> board[r][c] == currentPlayer } }) {
            return GameState.Win
        }

        return if (board.flatten().all { it.isNotEmpty() }) {
            GameState.Tie
        } else {
            GameState.Ongoing
        }
    }
}
