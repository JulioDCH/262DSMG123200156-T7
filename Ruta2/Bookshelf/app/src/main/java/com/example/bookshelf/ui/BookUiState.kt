package com.example.bookshelf.ui

import com.example.bookshelf.data.Book

sealed interface BookUiState {

    data class Success(
        val books: List<Book>
    ) : BookUiState

    data object Loading : BookUiState

    data object Error : BookUiState
}