package com.chip.challenge.data

import kotlinx.coroutines.flow.Flow

interface BreedRepository {

    fun getBreeds(): Flow<List<Breed>>

    fun getBreed(breedName: String): Flow<Breed>

    fun getRandomImages(breed: String): Flow<List<String>>

    suspend fun tryUpdateBreedsCache()
}
