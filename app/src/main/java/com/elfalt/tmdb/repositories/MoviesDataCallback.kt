package com.elfalt.tmdb.repositories

import com.elfalt.tmdb.remoteSource.Movie

interface MoviesDataCallback {

    fun onMoviesLoaded(MoviesList :List<Movie>)

    fun onError()
}