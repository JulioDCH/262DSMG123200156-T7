package com.example.amphibians.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewModelScope
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibianRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AmphibianViewModel(
    private val amphibianRepository: AmphibianRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<AmphibianUiState>(AmphibianUiState.Loading)

    val uiState: StateFlow<AmphibianUiState> =
        _uiState.asStateFlow()

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            _uiState.value = AmphibianUiState.Loading

            try {
                val amphibians = amphibianRepository.getAmphibians()
                _uiState.value = AmphibianUiState.Success(amphibians)
            } catch (e: Exception) {
                _uiState.value = AmphibianUiState.Error
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application =
                        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                                as AmphibiansApplication

                    AmphibianViewModel(
                        application.container.amphibianRepository
                    )
                }
            }
    }
}