package com.mwp.games4clues.frontend

import MemoryMatchViewModelFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mwp.games4clues.model.GameState
import com.mwp.games4clues.viewmodel.HomeViewModel
import com.mwp.games4clues.viewmodel.MemoryMatchViewModel

@Composable
fun MemoryMatchScreen(navController: NavController, level: Int, homeViewModel: HomeViewModel) {
    val viewModel: MemoryMatchViewModel = viewModel(
        factory = MemoryMatchViewModelFactory(level, homeViewModel)
    )
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, card ->
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        ClickableText(
                            text = androidx.compose.ui.text.AnnotatedString(if (card.isRevealed) card.value else "X"),
                            onClick = { viewModel.selectCard(rowIndex, colIndex) },
                            style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (state.gameState) {
            GameState.Win -> {
                Text("You Won!")
                Button(onClick = { homeViewModel.unlockNextLevel(level) }) {
                    Text("Next Level")
                }
            }
            GameState.Loss -> {
                Text("You Lost!")

            }
            GameState.Ongoing -> {
                // Game is ongoing, no specific action needed
            }
            GameState.Tie -> {
                // Handle tie state if applicable
            }
        }
    }
}
