package com.vfdegodoy.most_popular_movies.view

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.RequestListener
import com.vfdegodoy.most_popular_movies.constants.MovieConstants
import com.vfdegodoy.most_popular_movies.databinding.ActivityDetailsBinding
import com.vfdegodoy.most_popular_movies.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var mId : String
    private val mViewModel by viewModel<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)

        mId = intent.getStringExtra(MovieConstants.EXTRA.EXTRA_ID)?:String()

        setupObservers()

        mViewModel.detailMovie(mId)

        setContentView(binding.root)
    }

    private fun setupObservers(){
        mViewModel.movie.observe(this, {
            binding.tvReleaseDate.text = it.releaseDate
            binding.tvTitle.text = it.title?.title
            binding.tvGenres.text = it.genres.toString()
            binding.tvPlotSummary.text = it.plotOutline?.text

            Glide.with(this).asBitmap().load(it.title?.image?.url).listener(object : RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.clDetail.visibility = View.VISIBLE
                    binding.clLoadingContainer.visibility = View.INVISIBLE
                    return true
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.clDetail.visibility = View.VISIBLE
                    binding.clLoadingContainer.visibility = View.INVISIBLE
                    return false
                }

            }).into(binding.ivMovie)

        })
    }
}