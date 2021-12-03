package com.vfdegodoy.most_popular_movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vfdegodoy.most_popular_movies.constants.MovieConstants
import com.vfdegodoy.most_popular_movies.listener.APIListener
import com.vfdegodoy.most_popular_movies.listener.LoadingListener
import com.vfdegodoy.most_popular_movies.listener.LoadingStateListener
import com.vfdegodoy.most_popular_movies.model.MovieModel
import com.vfdegodoy.most_popular_movies.repository.MovieRepository
import com.vfdegodoy.most_popular_movies.util.MovieUtil

class MovieViewModel(
    private val mMovieRepository: MovieRepository
) : ViewModel() {

    private val mList = MutableLiveData<List<MovieModel>>()
    val list : LiveData<List<MovieModel>> = mList

    private val mMovie = MutableLiveData<MovieModel>()
    val movie : LiveData<MovieModel> = mMovie

    fun allList(){
        val listener = object : APIListener<List<String>> {
            override fun onSuccess(model: List<String>) {
                val movieList : MutableList<MovieModel> = mutableListOf()
                model.forEach{ id ->
                    movieList.add(MovieModel(MovieUtil.refineId(id)))
                }
                mList.value = movieList
            }

            override fun onFailure(error: String) {
                mList.value = listOf()
            }
        }
        mMovieRepository.movies(listener)
    }

    fun detailMovie(id : String){

        val listener = object : APIListener<MovieModel> {
            override fun onSuccess(model: MovieModel) {
                mMovie.value = model
            }

            override fun onFailure(error: String) {
                Log.d(MovieConstants.TAGS.LOAD_LIST_ERROR, error)
            }
        }

        mMovieRepository.movieDetail(id, listener)

    }


}