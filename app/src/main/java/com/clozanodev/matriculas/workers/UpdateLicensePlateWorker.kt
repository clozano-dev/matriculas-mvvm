package com.clozanodev.matriculas.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.clozanodev.matriculas.repository.PlateRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class UpdateLicensePlateWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val plateRepository: PlateRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

        val sharedPreferences = applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isGameLocked", false).apply()

        try {
            val licensePlate = plateRepository.getLicensePlateByDate()
            if (licensePlate != null) {
                Result.success()
            } else {
                Result.failure()
            }

        } catch (e: Exception) {
            Log.e("UpdateLicensePlateWorker", "Error updating license plate", e)

            Result.failure()
        }

    }
}