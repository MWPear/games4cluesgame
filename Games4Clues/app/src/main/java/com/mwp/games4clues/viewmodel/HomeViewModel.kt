package com.mwp.games4clues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwp.games4clues.Screen
import com.mwp.games4clues.model.GameLevel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _levels = MutableStateFlow<List<GameLevel>>(emptyList())
    val levels: StateFlow<List<GameLevel>> get() = _levels

    init {
        // Initialize levels with only the first one unlocked
        _levels.value = listOf(
            GameLevel("Play Tic Tac Toe - Level 1", Screen.TicTacToe.createRoute(1), 1, true),
            GameLevel("Play Tic Tac Toe - Level 2", Screen.TicTacToe.createRoute(2), 2, false),
            GameLevel("Play Maze - Level 1", Screen.Maze.createRoute(1), 1, false),
            GameLevel("Play Maze - Level 2", Screen.Maze.createRoute(2), 2, false),
            GameLevel("Play Memory Match - Level 1", Screen.MemoryMatch.createRoute(1), 1, false),
            GameLevel("Play Memory Match - Level 2", Screen.MemoryMatch.createRoute(2), 2, false),
            GameLevel("Solve Riddle", Screen.Riddle.route, 1, false)
        )
    }

    fun unlockNextLevel(currentLevel: GameLevel) {
        viewModelScope.launch {
            _levels.value = _levels.value.map { level ->
                if (level.level == currentLevel.level + 1) {
                    level.copy(isUnlocked = true)
                } else {
                    level
                }
            }
        }
    }
}
