package com.vfdegodoy.most_popular_movies.repository

import com.google.gson.Gson
import com.vfdegodoy.most_popular_movies.constants.MovieConstants
import com.vfdegodoy.most_popular_movies.listener.APIListener
import com.vfdegodoy.most_popular_movies.model.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val mRetrofit = RetrofitClient.createService(MostPopularMoviesService::class.java)

    fun movies(listener : APIListener<List<String>>){
        val call : Call<List<String>> = mRetrofit.getMostPopularMovies()

        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.code() != MovieConstants.HTTP.SUCCESS){
                    response.errorBody()?.let {
                        val error = Gson().fromJson(it.string(), String::class.java)
                        listener.onFailure(error)
                    }

                }else{
                    response.body()?.let{
                        listener.onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                listener.onFailure(t.localizedMessage?:MovieConstants.ERROR.UNEXPECTED_ERROR)
            }

        })
    }

    fun movieDetail(id : String, listener : APIListener<MovieModel>){
        val call : Call<MovieModel> = mRetrofit.getMovieDetail(id)

        call.enqueue(object : Callback<MovieModel> {
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if(response.code() != MovieConstants.HTTP.SUCCESS){
                    response.errorBody()?.let {
                        val error = it.toString()
                        listener.onFailure(error)
                    }
                }else{
                    response.body()?.let{
                        listener.onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                listener.onFailure(MovieConstants.ERROR.UNEXPECTED_ERROR)
            }

        })
    }
}