package com.clozanodev.matriculas.di

import android.content.Context
import com.clozanodev.matriculas.repository.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordModule {
    @Provides
    @Singleton
    fun provideWordRepository(@ApplicationContext context: Context): WordRepository {
        return WordRepository(context)
    }

}