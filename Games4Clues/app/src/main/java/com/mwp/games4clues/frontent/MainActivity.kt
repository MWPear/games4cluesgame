package com.mwp.games4clues.frontend

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.mwp.games4clues.frontent.MemoryMatchScreen
import com.mwp.games4clues.frontent.RiddleScreen
import com.mwp.games4clues.frontent.TicTacToeScreen
import com.mwp.games4clues.ui.theme.Games4CluesTheme
import com.mwp.games4clues.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Games4CluesTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(
            route = Screen.TicTacToe.route,
            arguments = listOf(navArgument("level") { type = NavType.IntType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getInt("level") ?: 1
            TicTacToeScreen(navController, level)
        }
        composable(
            route = Screen.Maze.route,
            arguments = listOf(navArgument("level") { type = NavType.IntType })
        ) { backStackEntry ->
            // Define your maze matrix here
            val mazeMatrix = listOf(
                listOf(2, 0, 1, 0, 0),
                listOf(1, 0, 1, 1, 0),
                listOf(0, 0, 0, 1, 0),
                listOf(0, 1, 1, 1, 0),
                listOf(0, 0, 0, 0, 3)
            )
            MazeScreen(navController = navController, mazeMatrix = mazeMatrix)
        }
        composable(
            route = Screen.MemoryMatch.route,
            arguments = listOf(navArgument("level") { type = NavType.IntType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getInt("level") ?: 1
            MemoryMatchScreen(navController, level)
        }
        composable(Screen.Riddle.route) { RiddleScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val levels by viewModel.levels.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Season 1") })
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(levels) { level ->
                    Button(
                        onClick = { navController.navigate(level.route) },
                        enabled = level.isUnlocked,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(if (level.isUnlocked) Color.LightGray else Color.Gray)
                    ) {
                        Text(level.name)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Games4CluesTheme {
        MainApp()
    }
}
