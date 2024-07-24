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
    private val plateRepository: PlateRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
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