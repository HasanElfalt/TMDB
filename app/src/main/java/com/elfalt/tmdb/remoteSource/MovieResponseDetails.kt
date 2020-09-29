package com.elfalt.tmdb.remoteSource

data class MovieResponseDetails(val backdrop_path : String, val original_title :String, val overview: String,
                                val poster_path : String, val tagline : String, val status : String,
                                val release_date : String, val vote_average : Double)