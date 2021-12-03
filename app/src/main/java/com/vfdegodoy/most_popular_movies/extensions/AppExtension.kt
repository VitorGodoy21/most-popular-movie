package com.vfdegodoy.most_popular_movies.extensions

import android.app.Application
import com.vfdegodoy.most_popular_movies.application.ApplicationModule
import com.vfdegodoy.most_popular_movies.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.startKoinApp() {
    startKoin {
        androidContext(this@startKoinApp)
        androidLogger()
        ApplicationModule.loadModules()
        loadKoinModules(appModule)
    }
}

val appModule = module {
    viewModel { MovieViewModel(get()) }
}