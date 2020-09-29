package com.elfalt.tmdb.remoteSource

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/{type}")
    fun getMovie(@Path("type") type : String,@Query("api_key") api_key : String) : Call<MovieResponse>


    @GET("/3/movie/{id}")
    fun getMovieDetails(@Path("id") id : String, @Query ("api_key") api_key: String) : Call<MovieResponseDetails>

    @GET("/3/tv/{type}")
    fun getTvShow(@Path("type") type: String, @Query("api_key") api_key : String) : Call<TvResponse>

    @GET("/3/tv/{id}")
    fun getTvShowDetails(@Path("id") id : String, @Query ("api_key") api_key: String) : Call<TvResponseDetails>


}