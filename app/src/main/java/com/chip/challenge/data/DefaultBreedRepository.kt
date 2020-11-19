package com.chip.challenge.data

import com.chip.challenge.api.DogService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
@ExperimentalCoroutinesApi
@FlowPreview
class DefaultBreedRepository @Inject constructor(
    private val breedDao: BreedDao,
    private val dogService: DogService
) : BreedRepository {

    override fun getBreeds(): Flow<List<Breed>> {
        return breedDao.getBreedsFlow()
    }

    override fun getBreed(breedName: String): Flow<Breed> {
        return breedDao.getBreed(breedName)
    }

    override fun getRandomImages(breed: String): Flow<List<String>> {
        return flow {
            emit(
                getRandomImagesFromRemote(breed)
            )
        }
    }

    private suspend fun getRandomImagesFromRemote(breed: String): List<String> {
        return try {
            dogService.breedRandomImage(breed, IMAGES_QUANTITY).message
        } catch (exception: Exception) {
            listOf()
        }
    }

    /**
     * Update the breeds cache.
     *
     * This function may decide to avoid making a network requests on every call based on a
     * cache-invalidation policy.
     */
    override suspend fun tryUpdateBreedsCache() {
        if (shouldUpdateBreedsCache()) fetchBreeds()
    }

    /**
     * Returns true if a network request should be made.
     */
    private suspend fun shouldUpdateBreedsCache(): Boolean {
        return true
    }

    /**
     * Fetch a new list of breeds from the network, and append them to [breedDao]
     */
    private suspend fun fetchBreeds() {
        val breeds = mutableListOf<Breed>()
        try {
            val breedNames = dogService.allBreeds()

            for (breedName in breedNames.message.keys) {
                val breed = Breed(name = breedName)
                breeds.add(breed)
            }
        } catch (exception: Exception) {

        }

        breedDao.insertAll(breeds)
    }

    companion object {
        private const val IMAGES_QUANTITY = 10
    }
}
