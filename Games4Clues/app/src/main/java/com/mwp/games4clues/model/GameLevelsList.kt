package com.mwp.games4clues.model

import com.mwp.games4clues.Screen

class GameLevelsList {
    val levels =
        listOf(
            GameLevel(Screen.TicTacToe.createRoute(1), 1, true),
            GameLevel(Screen.Maze.createRoute(1), 2, false),
            GameLevel(Screen.MemoryMatch.createRoute(1), 3, false),
            GameLevel(Screen.TicTacToe.createRoute(2), 4, false),
            GameLevel(Screen.MemoryMatch.createRoute(2), 5, false),
            GameLevel( Screen.Maze.createRoute(2), 6, false),
            GameLevel(Screen.Riddle.route, 1, true, "Solve Riddle")
        )
}