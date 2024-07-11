package com.mwp.games4clues.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.mwp.games4clues.model.GameLevel
import com.mwp.games4clues.model.GameLevelsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
    private val _levels = MutableStateFlow(GameLevelsList().levels)
    val levels: StateFlow<List<GameLevel>> get() = _levels

    init {
        loadUnlockedLevels()
    }

    private fun loadUnlockedLevels() {
        val unlockedLevels = sharedPreferences.getStringSet("unlocked_levels", emptySet())
        val updatedLevels = _levels.value.map { level ->
            level.copy(isUnlocked = unlockedLevels?.contains(level.route) == true || level.isUnlocked)
        }
        _levels.value = updatedLevels
    }

    fun unlockNextLevel(currentLevel: Int) {
        val currentLevelIndex = _levels.value.indexOfFirst { it.level == currentLevel }
        if (currentLevelIndex != -1 && currentLevelIndex < _levels.value.size - 1) {
            val updatedLevels = _levels.value.mapIndexed { index, level ->
                if (index == currentLevelIndex + 1) {
                    level.copy(isUnlocked = true)
                } else {
                    level
                }
            }
            _levels.value = updatedLevels
            saveUnlockedLevels()
        }
    }

    fun unlockLevelManually(levelRoute: String) {
        val updatedLevels = _levels.value.map { level ->
            if (level.route == levelRoute) {
                level.copy(isUnlocked = true)
            } else {
                level
            }
        }
        _levels.value = updatedLevels
        saveUnlockedLevels()
    }

    private fun saveUnlockedLevels() {
        val unlockedLevels = _levels.value.filter { it.isUnlocked }.map { it.route }.toSet()
        sharedPreferences.edit().putStringSet("unlocked_levels", unlockedLevels).apply()
    }
}


