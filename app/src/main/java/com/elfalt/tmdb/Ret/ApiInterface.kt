package com.elfalt.tmdb.Ret

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/popular")
    fun getMovie(@Query("api_key") api_key : String) : Call<MovieResponse>

    @GET("/3/movie/top_rated")
    fun getMovieTopRated(@Query("api_key") api_key : String) : Call<MovieResponse>

    @GET("/3/movie/now_playing")
    fun getMovieNowPlaying(@Query("api_key") api_key : String) : Call<MovieResponse>

    @GET("/3/movie/{id}")
    fun getMovieDetails(@Path("id") id : String, @Query ("api_key") api_key: String) : Call<MovieResponseDetails>

    @GET("/3/tv/{type}")
    fun getTvShow(@Path("type") type: String, @Query("api_key") api_key : String) : Call<TvResponse>

    @GET("/3/tv/{id}")
    fun getTvShowDetails(@Path("id") id : String, @Query ("api_key") api_key: String) : Call<TvResponseDetails>


}