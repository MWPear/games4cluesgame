package com.mwp.games4clues.frontent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mwp.games4clues.model.TicTacToeModel
import com.mwp.games4clues.viewmodel.TicTacToeViewModel
import com.mwp.games4clues.viewmodel.providers.TicTacToeViewModelFactory

@Composable
fun TicTacToeScreen(navController: NavController, level: Int) {
    val viewModel: TicTacToeViewModel = viewModel(factory = TicTacToeViewModelFactory(level))
    val state = viewModel.state.collectAsState()

    DefaultScreenLayout(title = "Tic Tac Toe - Level $level") { modifier ->
        TicTacToeGame(state.value, viewModel::makeMove, modifier)
    }
}

@Composable
fun TicTacToeGame(state: TicTacToeModel, onCellClick: (Int, Int) -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        state.winner?.let {
            Text("Winner: $it")
        }
        state.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.Gray)
                            .padding(4.dp)
                            .clickable { onCellClick(rowIndex, colIndex) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(cell, style = MaterialTheme.typography.headlineMedium)
                    }
                }
            }
        }
    }
}
