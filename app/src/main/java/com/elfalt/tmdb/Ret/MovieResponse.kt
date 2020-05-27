package com.elfalt.tmdb.Ret

data class MovieResponse(val results : List<Movie>)

data class Movie (val poster_path : String, val original_title: String, val release_date : String, val id : Int)