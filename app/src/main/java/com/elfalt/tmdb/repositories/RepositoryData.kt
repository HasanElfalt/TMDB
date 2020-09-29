package com.elfalt.tmdb.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elfalt.tmdb.AppConstants
import com.elfalt.tmdb.remoteSource.*
import com.elfalt.tmdb.localSource.AppDatabase
import com.elfalt.tmdb.ui.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RepositoryData {

    private val apiInterface = APIClient.getRetrofit().create(ApiInterface::class.java)

    private lateinit var moviesList : List<Movies>
    private lateinit var tvShowList  : List<TvShow>

    private lateinit var appDatabase: AppDatabase

    val movies : MutableLiveData<List<Movies>> by lazy { MutableLiveData<List<Movies>>() }
    val movieDetails : MutableLiveData<MovieResponseDetails> by lazy { MutableLiveData<MovieResponseDetails>() }
    val tvShowDetails : MutableLiveData<TvResponseDetails> by lazy { MutableLiveData<TvResponseDetails>() }
    val tvShowsPopular : MutableLiveData<List<TvShow>> by lazy { MutableLiveData<List<TvShow>>() }
    val tvShowsTopRated : MutableLiveData<List<TvShow>> by lazy { MutableLiveData<List<TvShow>>() }
    val tvShowsOnTheAir : MutableLiveData<List<TvShow>> by lazy { MutableLiveData<List<TvShow>>() }
    val tvShowsAiring : MutableLiveData<List<TvShow>> by lazy { MutableLiveData<List<TvShow>>() }

    fun getMovies(movieType : String,tBool : Int) : LiveData<List<Movies>>{

        if(!this::moviesList.isInitialized || tBool==1 ) {

            moviesList = getMoviesFromLocal()

            //If movies are exists in database, then post it to your liveData variable.
            if(moviesList.isNotEmpty()){
                movies.postValue(moviesList)
            }
            loadMoviesRemote(movieType)
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

                Log.e("Failure",t.message.toString())
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

                Log.e("Failure",t.message.toString())
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

        call.enqueue(object : Callback<TvResponseDetails> {

            override fun onResponse(
                call: Call<TvResponseDetails>,
                response: Response<TvResponseDetails>) {

                if(response.isSuccessful){

                    tvShowDetails.postValue(response.body()!!)

                }
            }

            override fun onFailure(call: Call<TvResponseDetails>, t: Throwable) {

                Log.e("Failure",t.message.toString())
            }

        })
        return tvShowDetails
    }

    private fun getMoviesFromLocal() : List<Movies>{
        return appDatabase.getMoviesDao().getAllPopularMovies()
    }

    private fun loadMoviesRemote(movieType: String){

        val call: Call<MovieResponse> = apiInterface.getMovie(movieType, AppConstants.API_KEY)

        call.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>) {

                if (response.isSuccessful) {
                    moviesList = getMappedMovies(response.body()!!.results)
                    appDatabase.getMoviesDao().insertAllMovies(moviesList)
                    movies.postValue(moviesList)
                }else{
                    Log.e("MarvelResponseError", response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

                Log.e("Failure", t.message.toString())
            }

        })

    }

    fun initDatabase(context: Context){
        if(!this:: appDatabase.isInitialized)
            appDatabase= AppDatabase.getDatabase(context)
    }

    fun getMappedMovies(movies : List<Movie>) : List<Movies>{

        val localMovies: MutableList<Movies> = mutableListOf()
        movies.forEach {
            localMovies.add(
                Movies(it.id,it.original_title,it.release_date,it.poster_path,it.vote_average)
            )
        }
        return localMovies
    }

}