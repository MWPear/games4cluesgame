package com.mwp.games4clues.viewmodel

import androidx.lifecycle.ViewModel
import com.mwp.games4clues.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _levels = MutableStateFlow(
        listOf(
            GameLevel("Play Tic Tac Toe - Level 1", Screen.TicTacToe.createRoute(1), 1, true),
            GameLevel("Play Tic Tac Toe - Level 2", Screen.TicTacToe.createRoute(2), 2, false),
            GameLevel("Play Maze - Level 1", Screen.Maze.createRoute(1), 1, true),
            GameLevel("Play Maze - Level 2", Screen.Maze.createRoute(2), 2, false),
            GameLevel("Play Memory Match - Level 1", Screen.MemoryMatch.createRoute(1), 1, true),
            GameLevel("Play Memory Match - Level 2", Screen.MemoryMatch.createRoute(2), 2, false),
            GameLevel("Solve Riddle", Screen.Riddle.route, 1, true)
        )
    )
    val levels: StateFlow<List<GameLevel>> get() = _levels

    fun unlockNextLevel(currentLevel: Int) {
        val newLevels = _levels.value.map {
            if (it.level == currentLevel + 1) {
                it.copy(isUnlocked = true)
            } else {
                it
            }
        }
        _levels.value = newLevels
    }
}

data class GameLevel(
    val name: String,
    val route: String,
    val level: Int,
    val isUnlocked: Boolean
)
