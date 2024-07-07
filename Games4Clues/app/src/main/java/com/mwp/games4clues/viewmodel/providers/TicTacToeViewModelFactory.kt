package com.mwp.games4clues.viewmodel.providers

import TicTacToeViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mwp.games4clues.viewmodel.HomeViewModel

class TicTacToeViewModelFactory(private val level: Int,
                                private val homeViewModel: HomeViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TicTacToeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TicTacToeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
