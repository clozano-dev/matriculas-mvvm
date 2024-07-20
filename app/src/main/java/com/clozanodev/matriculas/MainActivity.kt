package com.clozanodev.matriculas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.clozanodev.matriculas.ui.main.screens.MainScreen
import com.clozanodev.matriculas.ui.theme.MatriculasTheme
import com.clozanodev.matriculas.ui.main.ui.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatriculasTheme {
                MainScreen()
            }
        }
    }
}
