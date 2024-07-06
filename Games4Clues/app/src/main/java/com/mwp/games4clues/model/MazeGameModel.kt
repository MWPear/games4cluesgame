package com.mwp.games4clues.model

data class MazeGameState(
    val maze: List<List<Int>>,
    val playerPosition: Pair<Int, Int>,
    val endPosition: Pair<Int, Int>
)

class MazeGameModel(private val mazeMatrix: List<List<Int>>) {
    val start: Pair<Int, Int>
    val end: Pair<Int, Int>

    init {
        start = findPosition(2)
        end = findPosition(3)
    }

    fun getInitialState(): MazeGameState {
        return MazeGameState(maze = mazeMatrix, playerPosition = start, endPosition = end)
    }

    private fun findPosition(value: Int): Pair<Int, Int> {
        for (rowIndex in mazeMatrix.indices) {
            for (colIndex in mazeMatrix[rowIndex].indices) {
                if (mazeMatrix[rowIndex][colIndex] == value) {
                    return Pair(rowIndex, colIndex)
                }
            }
        }
        throw IllegalArgumentException("Value $value not found in maze")
    }

    fun movePlayer(currentPosition: Pair<Int, Int>, deltaX: Int, deltaY: Int): Pair<Int, Int> {
        val newX = currentPosition.first + deltaX
        val newY = currentPosition.second + deltaY
        return if (isValidMove(newX, newY)) Pair(newX, newY) else currentPosition
    }

    private fun isValidMove(x: Int, y: Int): Boolean {
        return x in mazeMatrix.indices && y in mazeMatrix[x].indices && mazeMatrix[x][y] != 1
    }
}
