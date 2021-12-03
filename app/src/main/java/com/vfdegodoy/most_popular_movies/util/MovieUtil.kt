package com.vfdegodoy.most_popular_movies.util

class MovieUtil private constructor(){
    companion object{
        fun refineId(fullId : String) : String{
            return fullId.replace("/title/", "").replace("/","")
        }
    }
}