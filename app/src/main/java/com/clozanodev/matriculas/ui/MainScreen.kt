package com.clozanodev.matriculas.ui

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clozanodev.matriculas.ui.home.HomeScreen
import com.clozanodev.matriculas.ui.navigation.BottomNavigationBar
import com.clozanodev.matriculas.ui.rules.RulesScreen
import com.clozanodev.matriculas.ui.statistics.StatisticsScreen
import com.clozanodev.matriculas.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
        bottomBar = {
            BottomNavigationBar(navController)
        }) { innerPadding ->
        NavHost(
            navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(viewModel) }
            composable("statistics") { StatisticsScreen(viewModel) }
            composable("rules") { RulesScreen() }
        }
    }
}
