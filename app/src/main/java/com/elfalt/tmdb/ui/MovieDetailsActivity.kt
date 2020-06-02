package com.elfalt.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elfalt.tmdb.MoviesAdapter
import com.elfalt.tmdb.R
import com.elfalt.tmdb.Ret.APIClient
import com.elfalt.tmdb.Ret.ApiInterface
import com.elfalt.tmdb.Ret.MovieResponseDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movieViewModel : MovieViewModel
    lateinit var movieId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        movieId = intent.getStringExtra("id")

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
