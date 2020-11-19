package com.chip.challenge.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.chip.challenge.data.Breed
import com.chip.challenge.data.BreedRepository
import kotlinx.coroutines.launch
import javax.inject.Named

class BreedsViewModel @ViewModelInject constructor(
    @Named("defaultRepository") private val repository: BreedRepository
) : ViewModel() {

    private val _snackbar = MutableLiveData<String?>()

    val snackbar: LiveData<String?>
        get() = _snackbar

    private val _spinner = MutableLiveData<Boolean>(false)

    val spinner: LiveData<Boolean>
        get() = _spinner

    val breeds: LiveData<List<Breed>> = repository.getBreeds().asLiveData()

    init {
        tryUpdateBreeds()
    }

    private fun tryUpdateBreeds() {
        try {
            _spinner.value = true
            viewModelScope.launch {
                repository.tryUpdateBreedsCache()
            }
        } catch (exception: Exception) {
            _snackbar.value = exception.message
        } finally {
            _spinner.value = false
        }
    }
}
