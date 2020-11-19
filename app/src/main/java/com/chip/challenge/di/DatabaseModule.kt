package com.chip.challenge.di

import android.content.Context
import com.chip.challenge.data.AppDatabase
import com.chip.challenge.data.BreedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideBreedDao(appDatabase: AppDatabase): BreedDao {
        return appDatabase.breedDao()
    }
}
