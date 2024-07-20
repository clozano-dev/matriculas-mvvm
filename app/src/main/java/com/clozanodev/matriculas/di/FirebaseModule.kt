package com.clozanodev.matriculas.di

import com.clozanodev.matriculas.data.remote.api.LicensePlateApi
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideLicensePlateApi(firestore: FirebaseFirestore): LicensePlateApi {
        return LicensePlateApi(firestore)
    }
}