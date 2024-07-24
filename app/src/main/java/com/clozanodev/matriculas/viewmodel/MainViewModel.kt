package com.clozanodev.matriculas.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clozanodev.matriculas.data.local.entities.UserStats
import com.clozanodev.matriculas.data.remote.entities.LicensePlate
import com.clozanodev.matriculas.game.GameLogic
import com.clozanodev.matriculas.repository.PlateRepository
import com.clozanodev.matriculas.repository.UserRepository
import com.clozanodev.matriculas.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val plateRepository: PlateRepository,
    private val userRepository: UserRepository,
    private val wordRepository: WordRepository,
    @ApplicationContext private val appContext: Context
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

    private val _isGameLocked = MutableStateFlow(false)
    val isGameLocked: StateFlow<Boolean> get() = _isGameLocked

    private val sharedPreferences = appContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    init {
        getUserStats()
        fetchCurrentLicensePlate(false)
        checkGameLockState()
    }

    private fun saveGameLockState(isLocked: Boolean) {
        sharedPreferences.edit().putBoolean("isGameLocked", isLocked).apply()
    }

    private fun checkGameLockState() {
        _isGameLocked.value = sharedPreferences.getBoolean("isGameLocked", false)
    }

    fun getUserStats() {
        viewModelScope.launch {
            try {
                val stats = userRepository.getUserStats()
                _userStats.value = stats
            } catch (e: Exception) {
                _insertStatus.value = e.message
            }
        }
    }

    fun refreshUserStats() {
        viewModelScope.launch {
            try {
                val stats = userRepository.getUserStats()
                _userStats.value = stats
            } catch (e: Exception) {
                _insertStatus.value = e.message
            }
        }
    }

    fun fetchCurrentLicensePlate(forceUnlock: Boolean = true) {
        viewModelScope.launch {
            try {
                _licensePlate.value = plateRepository.getLicensePlateByDate()
                if (forceUnlock) {
                    _isGameLocked.value = false
                    saveGameLockState(false)
                }
            } catch (e: Exception) {
                _insertStatus.value = "Error fetching license plate: ${e.message}"
            }
        }
    }

    fun calculateRealTimeScore(word: String) {
        val currentLicensePlate = licensePlate.value?.plate ?: return
        _realTimeScore.value = GameLogic.calculateScore(currentLicensePlate, word)
    }

    fun verifyWord(word: String): Boolean {
        return wordRepository.isWordValid(word)
    }

    fun submitWord(word: String) {
        if (_isGameLocked.value) return

        val currentLicensePlate = licensePlate.value?.plate ?: return
        val score = GameLogic.calculateScore(currentLicensePlate, word)
        _submittedWords.add(score)

        if (_submittedWords.size == 3) {
            _totalScore.value += _submittedWords.sum()
            _medal.value = GameLogic.getMedal(_totalScore.value)
            updateStats()
            lockGame()
        }
    }

    private fun lockGame() {
        _isGameLocked.value = true
        saveGameLockState(true)
    }

    private fun unlockGame() {
        _isGameLocked.value = false
        saveGameLockState(false)
    }

    private fun updateStats() {
        viewModelScope.launch {
            val currentStats = userStats.value ?: return@launch

            val newTotalPlayed = currentStats.totalDaysPlayed + 1

            val (newTotalGold, newCurrentConsecutiveGold, newMaxConsecutiveGold) = if (_medal.value == "Gold") {
                val updatedConsecutiveGold = currentStats.currentConsecutiveGold + 1
                val updatedMaxConsecutiveGold =
                    maxOf(currentStats.maxConsecutiveGold, updatedConsecutiveGold)
                Triple(
                    currentStats.totalGold + 1,
                    updatedConsecutiveGold,
                    updatedMaxConsecutiveGold
                )
            } else {
                Triple(currentStats.totalGold, 0, currentStats.maxConsecutiveGold)
            }


            val newTotalSilver =
                if (medal.value == "Silver") currentStats.totalSilver + 1 else currentStats.totalSilver
            val newTotalBronze =
                if (medal.value == "Bronze") currentStats.totalBronze + 1 else currentStats.totalBronze

            val newStats = currentStats.copy(
                totalDaysPlayed = newTotalPlayed,
                totalGold = newTotalGold,
                totalSilver = newTotalSilver,
                totalBronze = newTotalBronze,
                maxScore = maxOf(currentStats.maxScore, totalScore.value),
                lastDayResult = _medal.value,
                currentConsecutiveGold = newCurrentConsecutiveGold,
                maxConsecutiveGold = newMaxConsecutiveGold
            )

            userRepository.updateUserStats(newStats)
        }
    }
}