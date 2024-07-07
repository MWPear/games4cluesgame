package com.mwp.games4clues.frontend

import TicTacToeViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mwp.games4clues.model.GameState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeScreen(navController: NavController, level: Int, ticTacToeViewModel: TicTacToeViewModel = viewModel()) {
    val gameState by ticTacToeViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tic Tac Toe", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TicTacToeBoard(gameState.board) { row, col ->
            ticTacToeViewModel.makePlayerMove(row, col)
            if (gameState.gameState == GameState.Ongoing) {
                ticTacToeViewModel.makeComputerMove()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (gameState.gameState) {
            GameState.Ongoing -> {
                Text("Current turn: ${gameState.currentPlayer}")
            }
            GameState.Win -> {
                Text("${gameState.winner} wins!")
                Button(onClick = { ticTacToeViewModel.resetGame() }) {
                    Text("Play Again")
                }
            }
            GameState.Tie -> {
                Text("It's a tie!")
                Button(onClick = { ticTacToeViewModel.resetGame() }) {
                    Text("Play Again")
                }
            }
            GameState.Loss -> {
                Text("${gameState.winner} loses!")
                Button(onClick = { ticTacToeViewModel.resetGame() }) {
                    Text("Play Again")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeBoard(board: List<List<String>>, onCellClick: (Int, Int) -> Unit) {
    Column {
        board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    Button(
                        onClick = { onCellClick(rowIndex, colIndex) },
                        enabled = cell.isEmpty(),
                        modifier = Modifier.size(80.dp)
                    ) {
                        Text(cell)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTicTacToeScreen() {
    TicTacToeScreen(navController = rememberNavController(), level = 1)
}
