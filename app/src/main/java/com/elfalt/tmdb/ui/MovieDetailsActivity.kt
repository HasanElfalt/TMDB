package com.elfalt.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elfalt.tmdb.adapters.MoviesAdapter
import com.elfalt.tmdb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movieViewModel : MovieDetailsViewModel
    lateinit var movieId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movieViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        movieId = intent.getStringExtra("id").toString()

        movieViewModel.getMovieDetails(movieId).observe(this, Observer {

            Picasso.get().load(MoviesAdapter.BASE_IMAGE_URL + it.backdrop_path)
                .into(backdrop_path)

            Picasso.get().load(MoviesAdapter.BASE_IMAGE_URL + it.poster_path)
                .into(poster_path)

            movie_name.text = it.original_title
            overview_detail.text = it.overview
            tagLineDetails.text = it.tagline
            statusDetails.text = it.status
            releaseDateDetails.text = it.release_date
            vote_average_detail.text = it.vote_average.toString()

        })

    }
}
