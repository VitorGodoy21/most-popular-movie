package com.vfdegodoy.most_popular_movies.application

import com.vfdegodoy.most_popular_movies.repository.MovieRepository
import com.vfdegodoy.most_popular_movies.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object ApplicationModule {
    private val modules = module {

        factory { MovieRepository() }

        viewModel { MovieViewModel(get()) }
    }

    fun loadModules() {
        loadKoinModules(modules)
    }
}
