
package com.apps.bookshelf.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    @SerializedName("thumbnail")
    val thumbnail: String? = null
)

data class BooksResponse(
    val items: List<Book> = emptyList()
)
