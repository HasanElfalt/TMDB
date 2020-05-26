package com.elfalt.tmdb.Ret

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/popular")
    fun getMovie(@Query("api_key") api_key : String) : Call<MovieResponse>

}