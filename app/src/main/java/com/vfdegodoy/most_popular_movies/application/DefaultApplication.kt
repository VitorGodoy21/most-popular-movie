package com.vfdegodoy.most_popular_movies.application

import android.app.Application
import com.vfdegodoy.most_popular_movies.extensions.startKoinApp

class DefaultApplication : Application() {

    init {
        startKoinApp()
    }

}