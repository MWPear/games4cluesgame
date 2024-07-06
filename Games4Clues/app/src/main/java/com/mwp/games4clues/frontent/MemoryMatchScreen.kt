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
import com.mwp.games4clues.model.MemoryMatchGameState
import com.mwp.games4clues.viewmodel.MemoryMatchViewModel
import com.mwp.games4clues.viewmodel.providers.MemoryMatchViewModelFactory

@Composable
fun MemoryMatchScreen(navController: NavController, level: Int) {
    val viewModel: MemoryMatchViewModel = viewModel(factory = MemoryMatchViewModelFactory(level))
    val state = viewModel.state.collectAsState()

    DefaultScreenLayout(title = "Memory Match - Level $level") { modifier ->
        MemoryMatchGame(state.value, viewModel::selectCard, modifier)
    }
}

@Composable
fun MemoryMatchGame(state: MemoryMatchGameState, onCardClick: (Int, Int) -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        state.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, card ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(if (card.isRevealed) Color.Gray else Color.Blue)
                            .padding(4.dp)
                            .clickable { onCardClick(rowIndex, colIndex) },
                        contentAlignment = Alignment.Center
                    ) {
                        if (card.isRevealed) {
                            Text(card.value, style = MaterialTheme.typography.headlineSmall)
                        }
                    }
                }
            }
        }
    }
}
