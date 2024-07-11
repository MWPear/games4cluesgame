package com.mwp.games4clues.model.memorymatch

data class Card(val value: String, val isRevealed: Boolean = false, val isMatched: Boolean = false)

class MemoryMatchModel(private val level: Int) {
    private var state = MemoryMatchGameState(generateBoard(level))

    fun getState(): MemoryMatchGameState = state

    fun selectCard(row: Int, col: Int): MemoryMatchGameState {
        val card = state.board[row][col]
        if (card.isRevealed || card.isMatched) {
            return state
        }

        when {
            state.firstSelected == null -> {
                state = state.copy(firstSelected = Pair(row, col), board = revealCard(state.board, row, col))
            }
            state.secondSelected == null -> {
                val firstPos = state.firstSelected!!
                val firstCard = state.board[firstPos.first][firstPos.second]
                state = if (firstCard.value == card.value) {
                    state.copy(
                        secondSelected = Pair(row, col),
                        board = revealCard(state.board, row, col).markMatched(firstPos, Pair(row, col)),
                        matchesFound = state.matchesFound + 1
                    )
                } else {
                    state.copy(secondSelected = Pair(row, col), board = revealCard(state.board, row, col))
                }
            }
            else -> {
                state = state.copy(
                    firstSelected = Pair(row, col),
                    secondSelected = null,
                    board = hideCards(state.board).let { revealCard(it, row, col) }
                )
            }
        }

        return state
    }

    fun reset() {
        state = MemoryMatchGameState(generateBoard(level))
    }

    private fun generateBoard(level: Int): List<List<Card>> {
        val numPairs = when(level) {
            1 -> 5 // 10 cards (5 pairs)
            2 -> 8 // 16 cards (8 pairs)
            else -> level * level / 2
        }
        val values = (1..numPairs).flatMap { listOf(it.toString(), it.toString()) }.shuffled()
        val size = when(level) {
            1 -> 5 // 5x2 board for level 1
            2 -> 4 // 4x4 board for level 2
            else -> level
        }
        return values.chunked(size).map { row -> row.map { Card(it) } }
    }

    private fun revealCard(board: List<List<Card>>, row: Int, col: Int): List<List<Card>> {
        return board.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, card ->
                if (r == row && c == col) card.copy(isRevealed = true) else card
            }
        }
    }

    private fun hideCards(board: List<List<Card>>): List<List<Card>> {
        return board.map { row ->
            row.map { card ->
                if (card.isRevealed && !card.isMatched) {
                    card.copy(isRevealed = false)
                } else {
                    card
                }
            }
        }
    }

    private fun List<List<Card>>.markMatched(pos1: Pair<Int, Int>, pos2: Pair<Int, Int>): List<List<Card>> {
        return this.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, card ->
                if ((r == pos1.first && c == pos1.second) || (r == pos2.first && c == pos2.second)) {
                    card.copy(isMatched = true)
                } else {
                    card
                }
            }
        }
    }
}
