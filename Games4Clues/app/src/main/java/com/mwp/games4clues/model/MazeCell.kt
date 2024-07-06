package com.mwp.games4clues.model

sealed class MazeCell {
    object Walkway : MazeCell()
    object Wall : MazeCell()
    object StartPosition : MazeCell()
    object EndPosition : MazeCell()
}
