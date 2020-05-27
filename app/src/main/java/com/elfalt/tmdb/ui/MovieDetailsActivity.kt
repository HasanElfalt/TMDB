package com.elfalt.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    lateinit var movieId: String
    //const val BASE_POSTER_URL =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movieId = intent.getStringExtra("id")

        //Toast.makeText(this,"movie id = $movieId " , Toast.LENGTH_LONG).show()


        val apiInterface = APIClient.getRetrofit().create(ApiInterface::class.java)
        val call = apiInterface.getMovieDetails(movieId,
            MoviesActivity.api_key)

        call.enqueue(object : retrofit2.Callback<MovieResponseDetails> {

            override fun onResponse(
                call: Call<MovieResponseDetails>,
                response: Response<MovieResponseDetails>) {

                if(response.isSuccessful){

                    val res = response.body()!!
                    Picasso.get().load(MoviesAdapter.BASE_IMAGE_URL + res.backdrop_path)
                        .into(backdrop_path)

                    Picasso.get().load(MoviesAdapter.BASE_IMAGE_URL + res.poster_path)
                        .into(poster_path)

                    movie_name.text = res.original_title
                    overview_detail.text = res.overview
                    tagLineDetails.text = res.tagline
                    statusDetails.text = res.status
                    releaseDateDetails.text = res.release_date
                    vote_average_detail.text = res.vote_average.toString()

                }
            }

            override fun onFailure(call: Call<MovieResponseDetails>, t: Throwable) {

                Log.e("Failure",t.message)
            }

        })
    }
}
