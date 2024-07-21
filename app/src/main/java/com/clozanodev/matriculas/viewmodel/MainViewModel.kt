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
import com.clozanodev.matriculas.data.repository.PlateRepository
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
}