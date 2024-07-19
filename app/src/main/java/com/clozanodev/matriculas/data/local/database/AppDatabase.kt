package com.clozanodev.matriculas.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clozanodev.matriculas.data.local.dao.UserStatsDao
import com.clozanodev.matriculas.data.local.entities.UserStats

@Database(entities = [UserStats::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userStatsDao(): UserStatsDao
}