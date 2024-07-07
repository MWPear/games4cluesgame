package com.mwp.games4clues.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mwp.games4clues.Screen
import com.mwp.games4clues.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController, homeViewModel) }
        composable(
            route = Screen.TicTacToe.route,
            arguments = listOf(navArgument("level") { type = NavType.IntType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getInt("level") ?: 1
            TicTacToeScreen(navController, level,)
        }
        composable(
            route = Screen.Maze.route,
            arguments = listOf(navArgument("level") { type = NavType.IntType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getInt("level") ?: 1
            val mazeMatrix = when (level) {
                1 -> listOf(
                    listOf(2, 0, 1, 0, 0),
                    listOf(1, 0, 1, 1, 0),
                    listOf(0, 0, 0, 1, 0),
                    listOf(0, 1, 1, 1, 0),
                    listOf(0, 0, 0, 0, 3)
                )
                else -> listOf(
                    listOf(2, 1, 0, 1, 0),
                    listOf(1, 0, 1, 1, 0),
                    listOf(0, 0, 1, 0, 1),
                    listOf(1, 1, 1, 1, 0),
                    listOf(0, 0, 0, 0, 3)
                )
            }
            MazeScreen(navController = navController, mazeMatrix = mazeMatrix, homeViewModel = homeViewModel)
        }
        composable(
            route = Screen.MemoryMatch.route,
            arguments = listOf(navArgument("level") { type = NavType.IntType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getInt("level") ?: 1
            MemoryMatchScreen(navController, level, homeViewModel)
        }
        composable(Screen.Riddle.route) { RiddleScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    DefaultScreenLayout(title = "Season 1") {
        Column(modifier = Modifier.padding(top=200.dp)){
            homeViewModel.levels.collectAsState().value.forEach { level ->
                Button(
                    onClick = { navController.navigate(level.route) },
                    enabled = level.isUnlocked
                ) {
                    Text(level.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainApp()
}
