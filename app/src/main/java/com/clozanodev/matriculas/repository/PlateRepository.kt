package com.clozanodev.matriculas.repository

import com.clozanodev.matriculas.data.remote.api.LicensePlateApi
import com.clozanodev.matriculas.data.remote.entities.LicensePlate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class PlateRepository @Inject constructor(
    private val licensePlateApi: LicensePlateApi,
) {
    suspend fun getLicensePlate(date: String): LicensePlate? = licensePlateApi.getPlate(date)

    suspend fun getLicensePlateByDate(): LicensePlate? {
        val dateFormat = SimpleDateFormat("yyMMdd", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val currentDate = dateFormat.format(Date())
        return getLicensePlate(currentDate)
    }
}