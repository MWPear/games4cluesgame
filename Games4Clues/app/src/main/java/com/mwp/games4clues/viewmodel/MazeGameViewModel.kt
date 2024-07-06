package com.mwp.games4clues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mwp.games4clues.model.MazeGameModel
import com.mwp.games4clues.model.MazeGameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MazeGameViewModel(mazeMatrix: List<List<Int>>) : ViewModel() {
    private val gameModel = MazeGameModel(mazeMatrix)
    private val _state = MutableStateFlow(gameModel.getInitialState())
    val state: StateFlow<MazeGameState> get() = _state

    fun movePlayer(deltaX: Int, deltaY: Int) {
        val currentPosition = _state.value.playerPosition
        val newPosition = gameModel.movePlayer(currentPosition, deltaX, deltaY)
        _state.value = _state.value.copy(playerPosition = newPosition)
    }
}


