package com.clozanodev.matriculas.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clozanodev.matriculas.data.local.entities.UserStats
import com.clozanodev.matriculas.data.remote.entities.LicensePlate
import com.clozanodev.matriculas.game.GameLogic
import com.clozanodev.matriculas.repository.PlateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PlateRepository
) : ViewModel() {

    private val _insertStatus = MutableStateFlow<String?>(null)
    val insertStatus: StateFlow<String?> get() = _insertStatus

    private val _userStats = MutableStateFlow<UserStats?>(null)
    val userStats: StateFlow<UserStats?> get() = _userStats

    private val _licensePlate = MutableStateFlow<LicensePlate?>(null)
    val licensePlate: StateFlow<LicensePlate?> get() = _licensePlate

    private val _realTimeScore = MutableStateFlow(0)
    val realTimeScore: StateFlow<Int> get() = _realTimeScore

    private val _totalScore = MutableStateFlow(0)
    val totalScore: StateFlow<Int> get() = _totalScore

    private val _medal = MutableStateFlow("")
    val medal: StateFlow<String> get() = _medal

    private val _submittedWords = mutableListOf<Int>()

    init {
        getUserStats()
    }

    fun insertUserStats(userStats: UserStats) {
        viewModelScope.launch {
            try {
                repository.insertUserStats(userStats)
                _insertStatus.value = "User stats inserted successfully"
                Log.d("MainViewModel", "User stats inserted successfully")
            } catch (e: Exception) {
                _insertStatus.value = e.message
            }
        }
    }

    fun getUserStats() {
        viewModelScope.launch {
            try {
                val stats = repository.getUserStats()
                _userStats.value = stats
            } catch (e: Exception) {
                _insertStatus.value = e.message
            }
        }
    }

    fun getLicensePlate(id: Int) {
        viewModelScope.launch {
            try {
                _licensePlate.value = repository.getLicensePlate(id)
            } catch (e: Exception) {
                _insertStatus.value = "Error fetching license plate: ${e.message}"
            }
        }
    }

    fun calculateRealTimeScore(word: String) {
        val currentLicensePlate = licensePlate.value?.plate ?: return
        _realTimeScore.value = GameLogic.calculateScore(currentLicensePlate, word)
    }

    fun submitWord(word: String) {
        val currentLicensePlate = licensePlate.value?.plate ?: return
        val score = GameLogic.calculateScore(currentLicensePlate, word)
        _submittedWords.add(score)

        if (_submittedWords.size == 3) {
            _totalScore.value += _submittedWords.sum()
            _medal.value = GameLogic.getMedal(_totalScore.value)
        }
    }

    fun resetGame(){
        _realTimeScore.value = 0
        _submittedWords.clear()
        _totalScore.value = 0
        _medal.value = ""
    }

}