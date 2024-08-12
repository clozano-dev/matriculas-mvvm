package com.clozanodev.matriculas.di

import com.clozanodev.matriculas.repository.NotificationRepository
import com.clozanodev.matriculas.repository.NotificationRepositoryImpl
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideNotificationRepository(
        firebaseMessaging: FirebaseMessaging
    ): NotificationRepository {
        return NotificationRepositoryImpl(firebaseMessaging)
    }
}