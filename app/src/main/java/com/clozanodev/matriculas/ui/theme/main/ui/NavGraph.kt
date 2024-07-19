package com.clozanodev.matriculas.ui.theme.main.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clozanodev.matriculas.ui.theme.main.screens.MainScreen

@Composable
fun NavGraph(startDestination: String = "main"){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination){
        composable("main"){
            MainScreen()
        }
    }
}