package com.vfdegodoy.most_popular_movies.listener

class LoadingStateListener(val x : State) {

    fun state() : State = x

    enum class State{
        START, GET_ID, GET_MOVIE_DETAIL, FINISH
    }
}