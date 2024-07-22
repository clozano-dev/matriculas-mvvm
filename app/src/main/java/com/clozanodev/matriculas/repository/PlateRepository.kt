package com.clozanodev.matriculas.repository

import com.clozanodev.matriculas.data.local.dao.UserStatsDao
import com.clozanodev.matriculas.data.local.entities.UserStats
import com.clozanodev.matriculas.data.remote.api.LicensePlateApi
import com.clozanodev.matriculas.data.remote.entities.LicensePlate
import javax.inject.Inject

class PlateRepository @Inject constructor(
    private val licensePlateApi: LicensePlateApi,
) {
    suspend fun getLicensePlate(id: Int): LicensePlate? = licensePlateApi.getPlate(id)
    suspend fun getAllLicensePlates(): List<LicensePlate> = licensePlateApi.getAllPlates()

}