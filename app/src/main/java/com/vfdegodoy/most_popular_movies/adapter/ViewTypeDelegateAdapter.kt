package com.vfdegodoy.most_popular_movies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vfdegodoy.most_popular_movies.model.MovieModel

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: MovieModel)
}