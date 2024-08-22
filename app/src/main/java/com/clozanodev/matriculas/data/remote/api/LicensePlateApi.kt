package com.clozanodev.matriculas.data.remote.api

import com.clozanodev.matriculas.data.remote.entities.LicensePlate
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LicensePlateApi(firestore: FirebaseFirestore) {

    private val platesCollection = firestore.collection("licensePlates")

    suspend fun getPlate(id: String): LicensePlate? {
        val document = platesCollection.document(id).get().await()
        return if (document.exists()){
            document.toObject(LicensePlate::class.java)
        } else {
            null
        }
    }
}