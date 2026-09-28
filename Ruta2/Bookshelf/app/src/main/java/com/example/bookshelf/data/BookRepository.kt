package com.example.bookshelf.data

class BookRepository(
    private val bookApiService: BookApiService
) {

    suspend fun getBooks(): List<Book> {

        val response =
            bookApiService.searchBooks(
                "android programming",
                "AIzaSyBLknTHTVcm7K0szcdtaIy_mShv-ZRY4FA"
            )

        val books = mutableListOf<Book>()

        for (item in response.items.take(10)) {

            val detail =
                bookApiService.getBook(
                    item.id,
                    "AIzaSyBLknTHTVcm7K0szcdtaIy_mShv-ZRY4FA"
                )

            val thumbnail =
                detail.volumeInfo.imageLinks?.thumbnail
                    ?.replace("http://", "https://")

            if (thumbnail != null) {

                books.add(
                    Book(
                        id = item.id,
                        title = detail.volumeInfo.title,
                        thumbnail = thumbnail
                    )
                )
            }
        }

        return books
    }
}