package com.mwp.games4clues.viewmodel.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mwp.games4clues.viewmodel.MemoryMatchViewModel

class MemoryMatchViewModelFactory(private val level: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoryMatchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MemoryMatchViewModel(level) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}