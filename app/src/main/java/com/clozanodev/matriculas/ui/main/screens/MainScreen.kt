package com.clozanodev.matriculas.ui.main.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.clozanodev.matriculas.data.local.entities.UserStats
import com.clozanodev.matriculas.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    
    val insertStatus by viewModel.insertStatus.collectAsState()
    val licensePlate by viewModel.licensePlate.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            val userStats = UserStats(
                username = "test_user",
                maxScore = 100,
                totalGold = 10,
                totalSilver = 5,
                totalBronze = 3,
                totalDaysPlayed = 20,
                maxConsecutiveGold = 7,
                currentConsecutiveGold = 2
            )
            viewModel.insertUserStats(userStats)
        }) {
            Text("Insert User Stats")
        }

        Button(onClick = {
            viewModel.getLicensePlate(1)
        }) {
            Text("Get License Plate")
        }

        insertStatus?.let {
            Text(text = it)
        }

        licensePlate?.let {
            Text(text = "License Plate: ${it.plate}")
        }
    }
}