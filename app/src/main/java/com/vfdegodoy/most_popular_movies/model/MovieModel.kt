package com.vfdegodoy.most_popular_movies.model

import com.google.gson.annotations.SerializedName

data class MovieModel (
    @SerializedName("releaseDate")
    var releaseDate : String = "",

    @SerializedName("title")
    var title : TitleModel? = null,

    @SerializedName("genres")
    var genres : List<String>? = null,

    @SerializedName("plotOutline")
    var plotOutline : PlotOutlineModel? = null
)