package com.mwp.games4clues.frontend

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mwp.games4clues.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val listState = rememberLazyListState()
    val levels = homeViewModel.levels.collectAsState().value

    // Scroll to the bottom when the list is first displayed
    LaunchedEffect(key1 = levels.size) {
        listState.scrollToItem(0)
    }

    DefaultScreenLayout(title = "Season 1") {

        LazyColumn(
            state = listState,
            reverseLayout = true,
            modifier = Modifier.fillMaxWidth().padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(levels) { level ->
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
private fun HomePreview() {
    MainNavigation()
}