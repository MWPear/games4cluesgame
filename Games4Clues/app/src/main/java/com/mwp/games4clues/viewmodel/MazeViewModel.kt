package com.mwp.games4clues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwp.games4clues.model.GameState
import com.mwp.games4clues.model.maze.MazeGame
import com.mwp.games4clues.model.maze.MazeGameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MazeViewModel(private val mazeMatrix: List<List<Int>>, private val homeViewModel: HomeViewModel) : ViewModel() {
    private val _state = MutableStateFlow(MazeGameState(maze = mazeMatrix))
    val state: StateFlow<MazeGameState> get() = _state

    private val game = MazeGame(mazeMatrix)

    init {
        _state.value = game.getState()
    }

    fun movePlayer(deltaRow: Int, deltaCol: Int) {
        viewModelScope.launch {
            val newState = game.movePlayer(deltaRow, deltaCol)
            _state.value = newState
            if (newState.gameState == GameState.Win) {
                homeViewModel.unlockNextLevel(1) // Assuming level 1 here, adjust as necessary
            }
        }
    }
}
