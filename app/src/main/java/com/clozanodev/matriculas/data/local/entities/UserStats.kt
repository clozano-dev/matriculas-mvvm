package com.clozanodev.matriculas.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStats(
    @PrimaryKey val username: String,
    val maxScore: Int,
    val totalGold: Int,
    val totalSilver: Int,
    val totalBronze: Int,
    val totalDaysPlayed: Int,
    val maxConsecutiveGold: Int,
    val currentConsecutiveGold: Int
)
