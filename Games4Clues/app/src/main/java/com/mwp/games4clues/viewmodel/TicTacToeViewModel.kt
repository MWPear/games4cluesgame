package com.mwp.games4clues.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwp.games4clues.model.TicTacToeGame
import com.mwp.games4clues.model.TicTacToeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicTacToeViewModel(private val level: Int) : ViewModel() {
    private val _state = MutableStateFlow(TicTacToeModel())
    val state: StateFlow<TicTacToeModel> get() = _state

    private val game = TicTacToeGame(level)

    fun makeMove(row: Int, col: Int) {
        val currentState = game.getState()
        if (currentState.board[row][col].isNotEmpty() || currentState.isGameOver) {
            return
        }

        val newState = game.makePlayerMove(row, col)
        _state.value = newState

        if (!newState.isGameOver) {
            viewModelScope.launch {
                makeComputerMove()
            }
        }
    }

    private suspend fun makeComputerMove() {
        val currentState = game.makeComputerMove()
        _state.value = currentState
    }
}
