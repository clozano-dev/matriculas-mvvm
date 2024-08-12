package com.clozanodev.matriculas.repository

import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

interface NotificationRepository {
    fun subscribeToTopic(topic: String)
}

class NotificationRepositoryImpl @Inject constructor(
    private val firebaseMessaging: FirebaseMessaging
) : NotificationRepository {
    override fun subscribeToTopic(topic: String) {
        firebaseMessaging.subscribeToTopic(topic).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                }
            }
    }
}