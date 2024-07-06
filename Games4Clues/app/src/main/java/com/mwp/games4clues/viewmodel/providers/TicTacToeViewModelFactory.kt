package com.mwp.games4clues.viewmodel.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mwp.games4clues.viewmodel.TicTacToeViewModel

class TicTacToeViewModelFactory(private val level: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TicTacToeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TicTacToeViewModel(level) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

