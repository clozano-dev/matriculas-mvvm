package com.clozanodev.matriculas.data.repository

import com.clozanodev.matriculas.data.local.dao.UserStatsDao
import com.clozanodev.matriculas.data.local.entities.UserStats
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userStatsDao: UserStatsDao
) {
    suspend fun insertUserStats(userStats: UserStats) {
        userStatsDao.insertUserStats(userStats)
    }
    suspend fun getUserStats(username: String): UserStats? {
        return userStatsDao.getUserStats()
    }

}