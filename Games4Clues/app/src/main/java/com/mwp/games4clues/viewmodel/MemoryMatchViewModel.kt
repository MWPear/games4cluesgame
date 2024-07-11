package com.mwp.games4clues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwp.games4clues.model.GameState
import com.mwp.games4clues.model.memorymatch.MemoryMatchModel
import com.mwp.games4clues.model.memorymatch.MemoryMatchGameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MemoryMatchViewModel(private val level: Int, private val homeViewModel: HomeViewModel) : ViewModel() {
    private val _state = MutableStateFlow(MemoryMatchGameState())
    val state: StateFlow<MemoryMatchGameState> get() = _state

    private val game = MemoryMatchModel(level)

    init {
        _state.value = game.getState()
    }

    fun selectCard(row: Int, col: Int) {
        viewModelScope.launch {
            val newState = game.selectCard(row, col)
            _state.value = newState
            if (newState.matchesFound == (newState.board.flatten().size / 2)) {
                homeViewModel.unlockNextLevel(level)
                _state.value = _state.value.copy(gameState = GameState.Win)
            }
        }
    }

    fun resetGame() {
        game.reset()
        _state.value = game.getState().copy(gameState = GameState.Ongoing)
    }
}
