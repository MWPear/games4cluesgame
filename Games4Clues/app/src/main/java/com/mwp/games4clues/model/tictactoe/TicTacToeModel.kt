package com.mwp.games4clues.model.tictactoe

import com.mwp.games4clues.model.GameState

class TicTacToeModel(private val level: Int) {

    private var state = TicTacToeGameState()

    fun getState(): TicTacToeGameState = state

    fun makePlayerMove(row: Int, col: Int): TicTacToeGameState {
        if (state.board[row][col].isEmpty() && state.gameState == GameState.Ongoing) {
            val newBoard = updateBoard(row, col, state.currentPlayer)
            val gameState = checkGameState(newBoard, state.currentPlayer)
            state = state.copy(board = newBoard, currentPlayer = "O", gameState = gameState)
            if (gameState == GameState.Ongoing && level != 1) {
                makeComputerMove()
            }
        }
        return state
    }

    private fun updateBoard(row: Int, col: Int, player: String): List<List<String>> {
        return state.board.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, cell ->
                if (r == row && c == col) player else cell
            }
        }
    }

    fun makeComputerMove() {
        when (level) {
            1 -> makeRandomMove()
            2 -> makeSmartMove()
            3 -> makePerfectMove()
        }
        state = state.copy(currentPlayer = "X")
    }

    private fun makeRandomMove() {
        val emptyCells = mutableListOf<Pair<Int, Int>>()
        state.board.forEachIndexed { r, row ->
            row.forEachIndexed { c, cell ->
                if (cell.isEmpty()) {
                    emptyCells.add(Pair(r, c))
                }
            }
        }
        if (emptyCells.isNotEmpty()) {
            val (row, col) = emptyCells.random()
            val newBoard = updateBoard(row, col, "O")
            val gameState = checkGameState(newBoard, "O")
            state = state.copy(board = newBoard, gameState = gameState)
        }
    }

    private fun makeSmartMove() {
        // Implement logic for medium difficulty (if needed)
        makeRandomMove()
    }

    private fun makePerfectMove() {
        val bestMove = findBestMove(state.board)
        bestMove?.let {
            val newBoard = updateBoard(it.first, it.second, "O")
            val gameState = checkGameState(newBoard, "O")
            state = state.copy(board = newBoard, gameState = gameState)
        }
    }

    private fun findBestMove(board: List<List<String>>): Pair<Int, Int>? {
        var bestScore = Int.MIN_VALUE
        var bestMove: Pair<Int, Int>? = null

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j].isEmpty()) {
                    val newBoard = updateBoard(i, j, "O")
                    val score = minimax(newBoard, 0, false)
                    if (score > bestScore) {
                        bestScore = score
                        bestMove = Pair(i, j)
                    }
                }
            }
        }
        return bestMove
    }

    private fun minimax(board: List<List<String>>, depth: Int, isMaximizing: Boolean): Int {
        val gameState = checkGameState(board, if (isMaximizing) "O" else "X")
        return when (gameState) {
            GameState.Win -> if (isMaximizing) 10 - depth else depth - 10
            GameState.Loss -> if (isMaximizing) depth - 10 else 10 - depth
            GameState.Tie -> 0
            GameState.Ongoing -> if (isMaximizing) {
                var bestScore = Int.MIN_VALUE
                for (i in board.indices) {
                    for (j in board[i].indices) {
                        if (board[i][j].isEmpty()) {
                            val newBoard = updateBoard(i, j, "O")
                            val score = minimax(newBoard, depth + 1, false)
                            bestScore = maxOf(bestScore, score)
                        }
                    }
                }
                bestScore
            } else {
                var bestScore = Int.MAX_VALUE
                for (i in board.indices) {
                    for (j in board[i].indices) {
                        if (board[i][j].isEmpty()) {
                            val newBoard = updateBoard(i, j, "X")
                            val score = minimax(newBoard, depth + 1, true)
                            bestScore = minOf(bestScore, score)
                        }
                    }
                }
                bestScore
            }
        }
    }

    private fun checkGameState(board: List<List<String>>, currentPlayer: String): GameState {
        val lines = listOf(
            // Horizontal lines
            listOf(board[0][0], board[0][1], board[0][2]),
            listOf(board[1][0], board[1][1], board[1][2]),
            listOf(board[2][0], board[2][1], board[2][2]),
            // Vertical lines
            listOf(board[0][0], board[1][0], board[2][0]),
            listOf(board[0][1], board[1][1], board[2][1]),
            listOf(board[0][2], board[1][2], board[2][2]),
            // Diagonal lines
            listOf(board[0][0], board[1][1], board[2][2]),
            listOf(board[0][2], board[1][1], board[2][0])
        )
        for (line in lines) {
            if (line[0].isNotEmpty() && line[0] == line[1] && line[1] == line[2]) {
                return if (line[0] == currentPlayer) GameState.Loss else GameState.Win
            }
        }
        return if (isBoardFull(board)) GameState.Tie else GameState.Ongoing
    }

    private fun isBoardFull(board: List<List<String>>): Boolean {
        return board.all { row -> row.all { cell -> cell.isNotEmpty() } }
    }

    fun reset() {
        state = TicTacToeGameState()
    }
}
