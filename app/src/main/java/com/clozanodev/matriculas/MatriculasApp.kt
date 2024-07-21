package com.clozanodev.matriculas

import android.app.Application
import android.content.Context
import com.clozanodev.matriculas.data.local.database.AppDatabase
import com.clozanodev.matriculas.data.local.entities.UserStats
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class MatriculasApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDatabaseInitialized = sharedPreferences.getBoolean("is_Database_Initialized", false)

        if (!isDatabaseInitialized) {
            initializeDatabase()
            sharedPreferences.edit().putBoolean("is_Database_Initialized", true).apply()
        }
    }

    private fun initializeDatabase() {
        val db = AppDatabase.getDatabase(this)
        CoroutineScope(Dispatchers.IO).launch {
            val defaultUserStats = UserStats(
            maxScore = 0,
            totalGold = 0,
            totalSilver = 0,
            totalBronze = 0,
            totalDaysPlayed = 0,
            maxConsecutiveGold = 0,
            currentConsecutiveGold = 0,
            lastDayResult = ""
            )
            db.userStatsDao().insertUserStats(defaultUserStats)
        }
    }
}