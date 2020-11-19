package com.chip.challenge.di

import com.chip.challenge.api.DogService
import com.chip.challenge.data.BreedDao
import com.chip.challenge.data.BreedRepository
import com.chip.challenge.data.DefaultBreedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    @Named("defaultRepository")
    fun provideDefaultRepository(
        breedDao: BreedDao,
        service: DogService
    ): BreedRepository {
        return DefaultBreedRepository(breedDao, service)
    }
}
