package com.chip.challenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chip.challenge.data.BreedRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import javax.inject.Named

/**
 * The ViewModel used in [BreedDetailFragment].
 */
class BreedDetailViewModel @AssistedInject constructor(
    @Named("defaultRepository") private val repository: BreedRepository,
    @Assisted private val breedName: String
) : ViewModel() {

    fun searchPictures() = repository.getRandomImages(breedName)

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(breedName: String): BreedDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            breedName: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(breedName) as T
            }
        }
    }
}
