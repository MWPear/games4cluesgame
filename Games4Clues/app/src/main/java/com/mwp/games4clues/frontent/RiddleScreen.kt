package com.mwp.games4clues.frontent

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiddleScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Solve the Riddle") })
        },
        content = {
            RiddleGame()
        }
    )
}

@Composable
fun RiddleGame() {
    val riddle = "I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?"
    var answer by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(riddle)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Your Answer") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showResult = true }) {
            Text("Submit")
        }
        if (showResult) {
            Text(
                text = if (answer.equals("Echo", ignoreCase = true)) "Correct!" else "Incorrect, try again.",
                color = if (answer.equals("Echo", ignoreCase = true)) Color.Green else Color.Red
            )
        }
    }
}
