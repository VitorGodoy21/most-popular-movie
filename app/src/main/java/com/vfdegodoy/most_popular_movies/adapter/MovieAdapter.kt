package com.vfdegodoy.most_popular_movies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vfdegodoy.most_popular_movies.listener.MovieListener
import com.vfdegodoy.most_popular_movies.model.MovieModel

class MovieAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mList: ArrayList<MovieModel> = ArrayList()
    private lateinit var mMovieDelegateAdapter : MovieDelegateAdapter
    private lateinit var mListener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mMovieDelegateAdapter = MovieDelegateAdapter(mListener)
        return mMovieDelegateAdapter.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mMovieDelegateAdapter.onBindViewHolder(holder, mList[position])
    }

    override fun getItemCount(): Int = mList.size

    fun attachListener(listener: MovieListener){
        this.mListener = listener
    }

    fun updateList(list : List<MovieModel>){
        mList.addAll(list)
        notifyDataSetChanged()
    }


}