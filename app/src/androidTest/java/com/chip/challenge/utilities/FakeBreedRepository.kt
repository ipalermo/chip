package com.chip.challenge.utilities

import com.chip.challenge.data.Breed
import com.chip.challenge.data.BreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBreedsRepository :
    BreedRepository {

    override fun getBreeds(): Flow<List<Breed>> {
        return flow {
            emit(testBreeds)
        }
    }

    override fun getBreed(breedName: String): Flow<Breed> {
        TODO("Not yet implemented")
    }

    override fun getRandomImages(breed: String): Flow<List<String>> {
        return flow {
            emit(testBreedPhotos)
        }
    }

    override suspend fun tryUpdateBreedsCache() {
        TODO("Not yet implemented")
    }
}
