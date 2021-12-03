package com.vfdegodoy.most_popular_movies.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vfdegodoy.most_popular_movies.R
import com.vfdegodoy.most_popular_movies.extensions.safeInflate
import com.vfdegodoy.most_popular_movies.listener.MovieListener
import com.vfdegodoy.most_popular_movies.model.MovieModel

class MovieDelegateAdapter(val listener : MovieListener) : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MovieViewHolder(parent, listener)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: MovieModel) {
        holder as MovieViewHolder
        holder.bind(item)
    }

    class MovieViewHolder(parent: ViewGroup, val listener : MovieListener) :
        RecyclerView.ViewHolder(
            parent.safeInflate(parent.context, R.layout.list_item_movie, null).apply {
                layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        ) {

        private val idTextView: TextView = itemView.findViewById(R.id.tv_title)

        fun bind(item: MovieModel) {
            itemView.setOnClickListener {
                listener.onClick(item.releaseDate)
            }
            idTextView.text = item.releaseDate
        }
    }
}