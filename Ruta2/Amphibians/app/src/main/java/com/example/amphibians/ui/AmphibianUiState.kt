package com.example.amphibians.ui

import com.example.amphibians.data.Amphibian

sealed interface AmphibianUiState {
    data class Success(
        val amphibians: List<Amphibian>
    ) : AmphibianUiState

    data object Loading : AmphibianUiState

    data object Error : AmphibianUiState
}