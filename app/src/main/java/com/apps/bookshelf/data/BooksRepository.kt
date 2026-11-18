
package com.apps.bookshelf.data

import com.apps.bookshelf.model.Book
import com.apps.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(): List<Book>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(): List<Book> {
        val response = booksApiService.getBooks()
        return response.items
    }
}
