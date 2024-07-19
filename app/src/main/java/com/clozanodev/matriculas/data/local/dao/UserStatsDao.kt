package com.clozanodev.matriculas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clozanodev.matriculas.data.local.entities.UserStats

@Dao
interface UserStatsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUserStats(userStats: UserStats)

    @Query("SELECT * FROM user_stats WHERE username = :username")
    suspend fun getUserStats(username: String): UserStats?

    @Query("SELECT * FROM user_stats")
    suspend fun getAllUserStats(): List<UserStats>

}