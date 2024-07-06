package com.mwp.games4clues.model

data class TicTacToeModel(
    val board: List<List<String>> = List(3) { List(3) { "" } },
    val currentPlayer: String = "X",
    val winner: String? = null,
    val isGameOver: Boolean = false
)

class TicTacToeGame(private val level: Int) {

    private var state = TicTacToeModel()

    fun getState(): TicTacToeModel = state

    fun makePlayerMove(row: Int, col: Int): TicTacToeModel {
        if (state.board[row][col].isEmpty() && !state.isGameOver) {
            val newBoard = state.board.mapIndexed { r, rowList ->
                rowList.mapIndexed { c, cell ->
                    if (r == row && c == col) state.currentPlayer else cell
                }
            }
            val winner = checkWinner(newBoard)
            state = if (winner != null || isBoardFull(newBoard)) {
                state.copy(board = newBoard, winner = winner, isGameOver = true)
            } else {
                state.copy(board = newBoard, currentPlayer = "O")
            }
        }
        return state
    }

    fun makeComputerMove(): TicTacToeModel {
        val move = findBestMove(state.board)
        move?.let {
            val newBoard = state.board.mapIndexed { r, rowList ->
                rowList.mapIndexed { c, cell ->
                    if (r == it.first && c == it.second) "O" else cell
                }
            }
            val winner = checkWinner(newBoard)
            state = if (winner != null || isBoardFull(newBoard)) {
                state.copy(board = newBoard, winner = winner, isGameOver = true)
            } else {
                state.copy(board = newBoard, currentPlayer = "X")
            }
        }
        return state
    }

    private fun findBestMove(board: List<List<String>>): Pair<Int, Int>? {
        var bestScore = Int.MIN_VALUE
        var bestMove: Pair<Int, Int>? = null

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j].isEmpty()) {
                    val newBoard = board.mapIndexed { r, rowList ->
                        rowList.mapIndexed { c, cell ->
                            if (r == i && c == j) "O" else cell
                        }
                    }
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
        val winner = checkWinner(board)
        if (winner == "O") return 10 - depth
        if (winner == "X") return depth - 10
        if (isBoardFull(board)) return 0

        return if (isMaximizing) {
            var bestScore = Int.MIN_VALUE
            for (i in board.indices) {
                for (j in board[i].indices) {
                    if (board[i][j].isEmpty()) {
                        val newBoard = board.mapIndexed { r, rowList ->
                            rowList.mapIndexed { c, cell ->
                                if (r == i && c == j) "O" else cell
                            }
                        }
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
                        val newBoard = board.mapIndexed { r, rowList ->
                            rowList.mapIndexed { c, cell ->
                                if (r == i && c == j) "X" else cell
                            }
                        }
                        val score = minimax(newBoard, depth + 1, true)
                        bestScore = minOf(bestScore, score)
                    }
                }
            }
            bestScore
        }
    }

    private fun checkWinner(board: List<List<String>>): String? {
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
                return line[0]
            }
        }
        return null
    }

    private fun isBoardFull(board: List<List<String>>): Boolean {
        return board.all { row -> row.all { cell -> cell.isNotEmpty() } }
    }
}
