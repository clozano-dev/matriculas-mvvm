package com.clozanodev.matriculas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.clozanodev.matriculas.data.local.entities.UserStats

@Dao
interface UserStatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserStats(userStats: UserStats)

    @Query("SELECT * FROM user_stats WHERE id = 1")
    suspend fun getUserStats(): UserStats?

    @Update
    suspend fun updateUserStats(userStats: UserStats)


}