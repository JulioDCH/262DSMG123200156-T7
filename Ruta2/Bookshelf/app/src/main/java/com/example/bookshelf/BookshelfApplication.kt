package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.data.BookApiService
import com.example.bookshelf.data.BookRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookshelfApplication : Application() {

    val container = AppContainer()
}

class AppContainer {

    private val baseUrl =
        "https://www.googleapis.com/books/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val bookApiService =
        retrofit.create(BookApiService::class.java)

    val bookRepository =
        BookRepository(bookApiService)
}