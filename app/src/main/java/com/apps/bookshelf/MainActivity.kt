package com.apps.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.bookshelf.ui.screens.BooksScreen
import com.apps.bookshelf.ui.screens.BooksViewModel
import com.apps.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BookshelfTheme {
                BookshelfApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp() {

    val application =
        LocalContext.current.applicationContext as BookshelfApplication

    val repository = application.container.booksRepository

    val factory = remember(repository) {
        BooksViewModel.provideFactory(repository)
    }

    val booksViewModel: BooksViewModel = viewModel(
        factory = factory
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Bookshelf",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            )
        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            BooksScreen(
                booksUiState = booksViewModel.booksUiState,
                contentPadding = innerPadding
            )
        }
    }
}
