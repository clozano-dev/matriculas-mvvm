package com.clozanodev.matriculas.repository

import com.clozanodev.matriculas.data.local.dao.UserStatsDao
import com.clozanodev.matriculas.data.local.entities.UserStats
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userStatsDao: UserStatsDao
) {
    suspend fun getUserStats(): UserStats? {
        return userStatsDao.getUserStats()
    }
    suspend fun updateUserStats(userStats: UserStats) {
        userStatsDao.updateUserStats(userStats)
    }

}