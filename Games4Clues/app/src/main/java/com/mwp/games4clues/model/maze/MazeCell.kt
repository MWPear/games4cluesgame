package com.mwp.games4clues.model.maze

sealed class MazeCell {
    object Walkway : MazeCell()
    object Wall : MazeCell()
    object StartPosition : MazeCell()
    object EndPosition : MazeCell()
}
