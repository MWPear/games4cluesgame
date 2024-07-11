package com.mwp.games4clues.frontend

import MazeViewModelFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mwp.games4clues.model.maze.MazeGameState
import com.mwp.games4clues.viewmodel.HomeViewModel
import com.mwp.games4clues.viewmodel.MazeViewModel

@Composable
fun MazeScreen(navController: NavController, mazeMatrix: List<List<Int>>, homeViewModel: HomeViewModel) {
    val viewModel: MazeViewModel = viewModel(factory = MazeViewModelFactory(mazeMatrix, homeViewModel))
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        MazeGame(state, viewModel::movePlayer)
        Spacer(modifier = Modifier.height(16.dp))
        ControlPanel(viewModel::movePlayer)
    }
}

@Composable
fun MazeGame(state: MazeGameState, movePlayer: (Int, Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        state.maze.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                when (cell) {
                                    1 -> Color.Black
                                    2 -> Color.Green // Start point
                                    3 -> Color.Red // End point
                                    else -> Color.White
                                }
                            )
                            .border(1.dp, Color.Gray)
                            .clickable {
                                if (cell == 0) {
                                    movePlayer(rowIndex, colIndex)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (state.playerPosition == Pair(rowIndex, colIndex)) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(Color.Blue)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ControlPanel(movePlayer: (Int, Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { movePlayer(-1, 0) }) {
            Text("Up")
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = { movePlayer(0, -1) }) {
                Text("Left")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { movePlayer(0, 1) }) {
                Text("Right")
            }
        }
        Button(onClick = { movePlayer(1, 0) }) {
            Text("Down")
        }
    }
}
