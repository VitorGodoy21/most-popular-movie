package com.vfdegodoy.most_popular_movies.view

import android.content.Intent
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vfdegodoy.most_popular_movies.adapter.MovieAdapter
import com.vfdegodoy.most_popular_movies.constants.MovieConstants
import com.vfdegodoy.most_popular_movies.databinding.ActivityMainBinding
import com.vfdegodoy.most_popular_movies.listener.MovieListener
import com.vfdegodoy.most_popular_movies.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mViewModel by viewModel<MovieViewModel>()
    private var adapter : MovieAdapter? = null
    private lateinit var listener: MovieListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setupObservers()

        adapter = MovieAdapter()

        binding.rvMovies.apply {
            val linearLayout = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
            layoutManager = linearLayout
            clearOnScrollListeners()
            addItemDecoration(
                DividerItemDecoration(
                    binding.rvMovies.context,
                    linearLayout.orientation
                )
            )
        }

        binding.rvMovies.adapter = adapter

        listener = object : MovieListener{
            override fun onClick(movie: String) {
                val intent = Intent(baseContext, DetailsActivity::class.java)
                intent.putExtra(MovieConstants.EXTRA.EXTRA_ID, movie)
                startActivity(intent)
            }
        }

        adapter?.attachListener(listener)

        mViewModel.allList()

        setContentView(binding.root)
    }

    private fun setupObservers(){
        mViewModel.list.observe(this, {
            binding.rvMovies.post { adapter?.updateList(it) }
            binding.clLoadingContainer.visibility = View.GONE
            binding.rvMovies.visibility = View.VISIBLE
        })
    }
}