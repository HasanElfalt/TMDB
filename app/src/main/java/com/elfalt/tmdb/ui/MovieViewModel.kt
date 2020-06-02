package com.elfalt.tmdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elfalt.tmdb.Repositories.RepositoryData
import com.elfalt.tmdb.Ret.Movie

class MovieViewModel : ViewModel() {

    fun getMovies(type : String, tbool :Int) : LiveData<List<Movie>>{

        return RepositoryData.getMovies(type, tbool)
    }

}