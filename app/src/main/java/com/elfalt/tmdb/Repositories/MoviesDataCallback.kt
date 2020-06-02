package com.elfalt.tmdb.Repositories

import com.elfalt.tmdb.Ret.Movie

interface MoviesDataCallback {

    fun onMoviesLoaded(MoviesList :List<Movie>)

    fun onError()
}