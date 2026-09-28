package com.example.bookshelf.data

data class GoogleBooksResponse(
    val items: List<BookItem> = emptyList()
)

data class BookItem(
    val id: String
)