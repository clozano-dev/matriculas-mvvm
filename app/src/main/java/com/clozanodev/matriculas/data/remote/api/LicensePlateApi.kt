package com.clozanodev.matriculas.data.remote.api

import com.clozanodev.matriculas.data.remote.entities.LicensePlate
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LicensePlateApi(private val firestore: FirebaseFirestore) {

    private val platesCollection = firestore.collection("plates")

    suspend fun getPlate(id: Int): LicensePlate? {
        val document = platesCollection.document(id.toString()).get().await()
        return if (document.exists()){
            document.toObject(LicensePlate::class.java)
        } else {
            null
        }
    }

    suspend fun getAllPlates(): List<LicensePlate> {
        val snapshot = platesCollection.get().await()
        return snapshot.documents.mapNotNull { it.toObject(LicensePlate::class.java) }
    }

}