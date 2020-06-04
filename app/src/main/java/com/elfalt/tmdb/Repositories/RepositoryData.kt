package com.elfalt.tmdb.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elfalt.tmdb.AppConstants
import com.elfalt.tmdb.Ret.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RepositoryData {

    private lateinit var moviesList : List<Movie>
    private lateinit var tvShowList  : List<TvShow>

    val movies : MutableLiveData<List<Movie>> by lazy { MutableLiveData<List<Movie>>() }
    val movieDetails : MutableLiveData<MovieResponseDetails> by lazy { MutableLiveData<MovieResponseDetails>() }
    val tvShowDetails : MutableLiveData<TvResponseDetails> by lazy { MutableLiveData<TvResponseDetails>() }
    val tvShowsPopular : MutableLiveData<List<TvShow>> by lazy { MutableLiveData<List<TvShow>>() }
    val tvShowsTopRated : MutableLiveData<List<TvShow>> by lazy { MutableLiveData<List<TvShow>>() }
    val tvShowsOnTheAir : MutableLiveData<List<TvShow>> by lazy { MutableLiveData<List<TvShow>>() }
    val tvShowsAiring : MutableLiveData<List<TvShow>> by lazy { MutableLiveData<List<TvShow>>() }

    private val apiInterface = APIClient.getRetrofit().create(ApiInterface::class.java)


    fun getMovies(movieType : String,tBool : Int) : LiveData<List<Movie>>{

        if(!this::moviesList.isInitialized || tBool==1 ) {

            val call: Call<MovieResponse> = apiInterface.getMovie(movieType, AppConstants.API_KEY)

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

    fun getMovieDetail(movieId : String) : MutableLiveData<MovieResponseDetails>{

        val call = apiInterface.getMovieDetails(movieId,
            AppConstants.API_KEY)

        call.enqueue(object : retrofit2.Callback<MovieResponseDetails> {

            override fun onResponse(
                call: Call<MovieResponseDetails>,
                response: Response<MovieResponseDetails>) {

                if(response.isSuccessful){

                    movieDetails.postValue(response.body()!!)

                }
            }

            override fun onFailure(call: Call<MovieResponseDetails>, t: Throwable) {

                Log.e("Failure",t.message)
            }

        })
        return movieDetails
    }

    fun getTvShows(tvType : String) : MutableLiveData<List<TvShow>>{

        val call : Call<TvResponse> = apiInterface.getTvShow(tvType, AppConstants.API_KEY)

        call.enqueue(object : Callback<TvResponse> {

            override fun onResponse(
                call: Call<TvResponse>,
                response: Response<TvResponse>) {

                if(response.isSuccessful){

                    tvShowList = response.body()!!.results

                    if(tvType == AppConstants.POPULAR)   { tvShowsPopular.postValue(tvShowList) }
                    else if (tvType == AppConstants.TOP_RATED){ tvShowsTopRated.postValue(tvShowList) }
                    else if (tvType == AppConstants.ON_THE_AIR){ tvShowsOnTheAir.postValue(tvShowList) }
                    else { tvShowsAiring.postValue(tvShowList) }
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {

                Log.e("Failure",t.message)
            }

        })
        return when(tvType){
            AppConstants.POPULAR -> tvShowsPopular
            AppConstants.TOP_RATED -> tvShowsTopRated
            AppConstants.ON_THE_AIR -> tvShowsOnTheAir
            else -> tvShowsAiring
        }
    }

    fun getTvShowsDetails(tvId : String) : MutableLiveData<TvResponseDetails>{

        val call = apiInterface.getTvShowDetails(tvId, AppConstants.API_KEY)

        call.enqueue(object : retrofit2.Callback<TvResponseDetails> {

            override fun onResponse(
                call: Call<TvResponseDetails>,
                response: Response<TvResponseDetails>) {

                if(response.isSuccessful){

                    tvShowDetails.postValue(response.body()!!)

                }
            }

            override fun onFailure(call: Call<TvResponseDetails>, t: Throwable) {

                Log.e("Failure",t.message)
            }

        })
        return tvShowDetails
    }

}