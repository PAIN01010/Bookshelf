
package com.apps.bookshelf

import android.app.Application
import com.apps.bookshelf.data.AppContainer
import com.apps.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = DefaultAppContainer()
    }
}
