package com.mwp.games4clues.model.maze

import com.mwp.games4clues.model.GameState

class MazeGame(private val mazeMatrix: List<List<Int>>) {
    private var state = MazeGameState(maze = mazeMatrix)

    fun getState(): MazeGameState = state

    fun movePlayer(deltaRow: Int, deltaCol: Int): MazeGameState {
        val newPosition = calculateNewPosition(state.playerPosition, deltaRow, deltaCol)
        if (isValidMove(newPosition)) {
            state = state.copy(playerPosition = newPosition)
            if (newPosition == findEndPosition()) {
                state = state.copy(gameState = GameState.Win)
            }
        }
        return state
    }

    private fun calculateNewPosition(position: Pair<Int, Int>, deltaRow: Int, deltaCol: Int): Pair<Int, Int> {
        return Pair(position.first + deltaRow, position.second + deltaCol)
    }

    private fun isValidMove(position: Pair<Int, Int>): Boolean {
        val (row, col) = position
        return row in mazeMatrix.indices && col in mazeMatrix[0].indices && mazeMatrix[row][col] != 1
    }

    private fun findEndPosition(): Pair<Int, Int> {
        mazeMatrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, cell ->
                if (cell == 3) return Pair(rowIndex, colIndex)
            }
        }
        return Pair(-1, -1) // Default case, should not be reached if maze is valid
    }
}
