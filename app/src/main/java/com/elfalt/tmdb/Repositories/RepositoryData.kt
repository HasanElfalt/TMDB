package com.elfalt.tmdb.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elfalt.tmdb.Ret.APIClient
import com.elfalt.tmdb.Ret.ApiInterface
import com.elfalt.tmdb.Ret.Movie
import com.elfalt.tmdb.Ret.MovieResponse
import com.elfalt.tmdb.ui.MoviesActivity.Companion.api_key
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RepositoryData {

    private lateinit var moviesList : List<Movie>

    val movies : MutableLiveData<List<Movie>> by lazy { MutableLiveData<List<Movie>>() }

    private val apiInterface = APIClient.getRetrofit().create(ApiInterface::class.java)


    fun getMovies(movieType : String,tBool : Int) : LiveData<List<Movie>>{

        if(!this::moviesList.isInitialized || tBool==1) {

            val call: Call<MovieResponse> = apiInterface.getMovie(movieType, api_key)

            call.enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>) {

                    if (response.isSuccessful) {
                        moviesList = response.body()!!.results
                        movies.postValue(moviesList)
                    }else{
                        Log.e("MarvelResponseError", response.message())
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

                    Log.e("Failure", t.message)
                }

            })
        }
        return movies
    }

}