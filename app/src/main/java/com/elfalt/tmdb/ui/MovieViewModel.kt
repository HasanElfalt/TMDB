package com.elfalt.tmdb.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elfalt.tmdb.Repositories.RepositoryData
import com.elfalt.tmdb.Ret.Movie
import com.elfalt.tmdb.Ret.MovieResponseDetails
import com.elfalt.tmdb.Ret.TvResponseDetails
import com.elfalt.tmdb.Ret.TvShow

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    init {
        RepositoryData.initDatabase(application)
    }



    fun getMovies(type : String, tbool :Int) : LiveData<List<Movies>>{
        return RepositoryData.getMovies(type, tbool)
    }

    fun getMovieDetails(movieId : String) : LiveData<MovieResponseDetails>{
        return RepositoryData.getMovieDetail(movieId)
    }

    fun getTvShowDetails(tvId : String): LiveData<TvResponseDetails>{
        return RepositoryData.getTvShowsDetails(tvId)
    }

    fun getTvShows(tvType : String) : LiveData<List<TvShow>>{
        return RepositoryData.getTvShows(tvType)
    }

}