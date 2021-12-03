package com.vfdegodoy.most_popular_movies.model

import com.google.gson.annotations.SerializedName

class TitleModel {
    @SerializedName("title")
    var title : String = ""

    @SerializedName("titleType")
    var titleType : String = ""

    @SerializedName("image")
    var image : ImageModel? = null

    class ImageModel{
        @SerializedName("url")
        var url : String = ""
    }
}