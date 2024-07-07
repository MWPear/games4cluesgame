package com.mwp.games4clues.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mwp.games4clues.model.GameState
import com.mwp.games4clues.viewmodel.HomeViewModel
import com.mwp.games4clues.viewmodel.TicTacToeViewModel
import com.mwp.games4clues.viewmodel.providers.TicTacToeViewModelFactory

@Composable
fun TicTacToeScreen(navController: NavController, level: Int, homeViewModel: HomeViewModel) {
    val viewModel: TicTacToeViewModel = viewModel(
        factory = TicTacToeViewModelFactory(level)
    )
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        ClickableText(
                            text = androidx.compose.ui.text.AnnotatedString(cell),
                            onClick = { viewModel.makePlayerMove(rowIndex, colIndex) },
                            style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (state.gameState) {
            GameState.Win -> {
                Text("Winner: ${state.winner}")
                Button(onClick = { homeViewModel.unlockNextLevel(level) }) {
                    Text("Next Level")
                }
            }
            GameState.Tie -> {
                Text("Game Over: Tie")
                Button(onClick = { viewModel.resetGame() }) {
                    Text("Restart")
                }
            }
            else -> { /* Game is ongoing */ }
        }
    }
}
