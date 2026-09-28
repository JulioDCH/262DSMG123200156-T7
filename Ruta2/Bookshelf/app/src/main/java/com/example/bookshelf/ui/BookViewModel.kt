package com.example.bookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<BookUiState>(BookUiState.Loading)

    val uiState: StateFlow<BookUiState> =
        _uiState.asStateFlow()

    init {
        getBooks()
    }

    private fun getBooks() {

        viewModelScope.launch {

            try {

                val books =
                    bookRepository.getBooks()

                _uiState.value =
                    BookUiState.Success(books)

            } catch (e: Exception) {

                e.printStackTrace()

                _uiState.value =
                    BookUiState.Error
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory =
            viewModelFactory {

                initializer {

                    val application =
                        this[
                            ViewModelProvider
                                .AndroidViewModelFactory
                                .APPLICATION_KEY
                        ] as BookshelfApplication

                    BookViewModel(
                        application.container.bookRepository
                    )
                }
            }
    }
}