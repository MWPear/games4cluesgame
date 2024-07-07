package com.mwp.games4clues.frontend

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultScreenLayout(title: String, content: @Composable (Modifier) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        },
        content = { paddingValues ->
            content(Modifier.padding(paddingValues))
        }
    )
}