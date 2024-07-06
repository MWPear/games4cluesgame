package com.mwp.games4clues.viewmodel

import androidx.lifecycle.ViewModel
import com.mwp.games4clues.model.MemoryMatchGameState
import com.mwp.games4clues.model.MemoryMatchModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MemoryMatchViewModel(level: Int) : ViewModel() {
    private val game = MemoryMatchModel(level)
    private val _state = MutableStateFlow(game.getState())
    val state: StateFlow<MemoryMatchGameState> get() = _state

    fun selectCard(row: Int, col: Int) {
        val newState = game.selectCard(row, col)
        _state.value = newState
    }
}
