package com.clozanodev.matriculas.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.clozanodev.matriculas.repository.PlateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateLicensePlateWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val plateRepository: PlateRepository,
    private val appContext: Context
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

        val sharedPreferences = appContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isGameLocked", false).apply()

        try {
            val licensePlate = plateRepository.getLicensePlateByDate()
            if (licensePlate != null) {
                Result.success()
            } else {
                Result.failure()
            }

        } catch (e: Exception) {
            Result.failure()
        }

    }
}