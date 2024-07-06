package com.mwp.games4clues.viewmodel.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mwp.games4clues.viewmodel.MazeGameViewModel

class MazeGameViewModelFactory(private val mazeMatrix: List<List<Int>>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MazeGameViewModel::class.java)) {
            return MazeGameViewModel(mazeMatrix) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}