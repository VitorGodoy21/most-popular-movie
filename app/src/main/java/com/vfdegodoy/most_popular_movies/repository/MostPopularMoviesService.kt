package com.vfdegodoy.most_popular_movies.repository

import com.vfdegodoy.most_popular_movies.model.MovieModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MostPopularMoviesService {

    @GET("title/get-most-popular-movies")
    fun getMostPopularMovies() : Call<List<String>>

    @GET("title/get-overview-details")
    fun getMovieDetail(@Query("tconst") id: String) : Call<MovieModel>

}