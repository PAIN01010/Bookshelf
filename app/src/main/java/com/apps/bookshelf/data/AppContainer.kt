
package com.apps.bookshelf.data

import com.apps.bookshelf.network.BooksApi
import com.apps.bookshelf.network.BooksApiService

interface AppContainer {
    val booksRepository: BooksRepository
}

class DefaultAppContainer : AppContainer {

    private val booksApiService: BooksApiService by lazy {
        BooksApi.retrofitService
    }

    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(
            booksApiService = booksApiService
        )
    }
}
