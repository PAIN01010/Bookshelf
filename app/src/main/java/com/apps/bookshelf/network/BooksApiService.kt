
package com.apps.bookshelf.network

import com.apps.bookshelf.BuildConfig
import com.apps.bookshelf.model.BooksResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL =
    "https://www.googleapis.com/books/v1/"

interface BooksApiService {

    @Headers(
        "X-Android-Package: com.apps.bookshelf",
        "X-Android-Cert: 0CCE1DE509B88CA0BB7FAFCC4F8A3D632CD3366C"
    )
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String = "jazz history",
        @Query("maxResults") maxResults: Int = 40,
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): BooksResponse
}

object BooksApi {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}
