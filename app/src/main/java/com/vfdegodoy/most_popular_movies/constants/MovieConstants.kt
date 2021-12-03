package com.vfdegodoy.most_popular_movies.constants

class MovieConstants private constructor() {
    object HTTP{
        const val SUCCESS = 200
    }

    object EXTRA{
        const val EXTRA_ID = "EXTRA_ID"
    }

    object TAGS {
        const val LOAD_LIST_ERROR = "LOAD_LIST_ERROR"
    }

    object ERROR {
        const val UNEXPECTED_ERROR = "ERRO INESPERADO"
    }

    object LOADING {
        const val LIST = "Carregando a lista."
    }

}