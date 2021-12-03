package com.vfdegodoy.most_popular_movies.repository

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){
    companion object{
        private lateinit var retrofit : Retrofit
        private var baseUrl = "https://imdb8.p.rapidapi.com/"
        private var xRapidApiHost = String()
        private var xRapidApiKey = String()

        private fun getRetrofitInstance() : Retrofit {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request =
                        chain.request()
                            .newBuilder()
                            .addHeader("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                            .addHeader("X-RapidAPI-Key", "ec27e6454emsh561b91fb5fe86a6p102beajsnfb6bb2c8ffc2")
                            .build()
                    return chain.proceed(request)
                }

            })

            if(!Companion::retrofit.isInitialized){
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        fun addHeaders(xRapidApiHost : String, xRapidApiKey : String){
            this.xRapidApiHost = xRapidApiHost
            this.xRapidApiKey = xRapidApiKey
        }

        fun <S> createService(serviceClass : Class<S>) : S {
            return getRetrofitInstance().create(serviceClass)
        }

    }
}