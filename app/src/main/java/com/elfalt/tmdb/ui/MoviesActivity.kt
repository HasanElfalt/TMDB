package com.elfalt.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.elfalt.tmdb.MoviesAdapter
import com.elfalt.tmdb.R
import com.elfalt.tmdb.Ret.APIClient
import com.elfalt.tmdb.Ret.ApiInterface
import com.elfalt.tmdb.Ret.Movie
import com.elfalt.tmdb.Ret.MovieResponse
import kotlinx.android.synthetic.main.activity_movies.*
import retrofit2.Call
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        getRecyclerViewDate(R.id.popular_tab)

        popular_tab.setOnClickListener { getRecyclerViewDate(R.id.popular_tab) }

        top_Rated_tab.setOnClickListener { getRecyclerViewDate(R.id.top_Rated_tab) }

        Now_Playing_tab.setOnClickListener {  getRecyclerViewDate(R.id.Now_Playing_tab) }


    }
    fun getRecyclerViewDate(id : Int){

        val apiInterface = APIClient.getRetrofit().create(ApiInterface::class.java)
        var call : Call<MovieResponse> = apiInterface.getMovie(api_key)

        when(id){

            R.id.popular_tab -> call = apiInterface.getMovie(
                api_key
            )

            R.id.top_Rated_tab -> call = apiInterface.getMovieTopRated(
                api_key
            )

            R.id.Now_Playing_tab -> call = apiInterface.getMovieNowPlaying(
                api_key
            )
        }

        call.enqueue(object : retrofit2.Callback<MovieResponse> {

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {

                if(response.isSuccessful){
                    val movieList = response.body()!!.results
                    populateMovieRecycler(movieList)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

                Log.e("Failure",t.message)
            }

        })

    }

    private fun populateMovieRecycler(moviesList: List<Movie>) {
        movieRecyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        movieRecyclerView.adapter = MoviesAdapter(moviesList)

    }

    companion object {
        const val api_key : String = "0948e9d82b480fe80240ad8610c1d77b"
    }
}
