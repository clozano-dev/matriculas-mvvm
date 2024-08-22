package com.clozanodev.matriculas

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.clozanodev.matriculas.ui.MainScreen
import com.clozanodev.matriculas.ui.theme.MatriculasTheme
import com.clozanodev.matriculas.viewmodel.MainViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
            } else {
                Toast.makeText(
                    this,
                    "Notificaciones deshabilitadas. Por favor, habilite las notificaciones en la configuración de la aplicación.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatriculasTheme {
                MainScreen()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
