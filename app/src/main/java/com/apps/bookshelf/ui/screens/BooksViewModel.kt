
package com.apps.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apps.bookshelf.data.BooksRepository
import com.apps.bookshelf.model.Book
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BooksUiState {
    data class Success(
        val books: List<Book>
    ) : BooksUiState

    data object Loading : BooksUiState

    data object Error : BooksUiState
}

class BooksViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(
        BooksUiState.Loading
    )
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading

            booksUiState = try {
                val books = booksRepository.getBooks()
                BooksUiState.Success(books)
            } catch (e: CancellationException) {
                throw e
            } catch (e: IOException) {
                BooksUiState.Error
            } catch (e: HttpException) {
                BooksUiState.Error
            } catch (e: Exception) {
                BooksUiState.Error
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: BooksRepository
        ): ViewModelProvider.Factory {

            return object : ViewModelProvider.Factory {

                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>
                ): T {
                    return BooksViewModel(repository) as T
                }
            }
        }
    }
}
