package com.vfdegodoy.most_popular_movies.listener

interface APIListener<T> {
    fun onSuccess(model : T)
    fun onFailure(error : String)
}