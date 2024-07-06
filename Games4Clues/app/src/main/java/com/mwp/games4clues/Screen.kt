package com.mwp.games4clues

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object TicTacToe : Screen("tictactoe/{level}") {
        fun createRoute(level: Int) = "tictactoe/$level"
    }

    object Maze : Screen("maze/{level}") {
        fun createRoute(level: Int) = "maze/$level"
    }

    object MemoryMatch : Screen("memorymatch/{level}") {
        fun createRoute(level: Int) = "memorymatch/$level"
    }

    object Riddle : Screen("riddle") // No levels for the riddle
}