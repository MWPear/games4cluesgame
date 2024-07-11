package com.mwp.games4clues.frontend

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mwp.games4clues.Screen
import com.mwp.games4clues.model.maze.MazeMaps
import com.mwp.games4clues.viewmodel.HomeViewModel

@Composable
fun MainNavigation() {
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
            val mazeMatrix = MazeMaps().getMap(level)
            MazeScreen(navController = navController,
                mazeMatrix = mazeMatrix,
                homeViewModel = homeViewModel)
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