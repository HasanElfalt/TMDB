package com.elfalt.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.elfalt.tmdb.R
import com.elfalt.tmdb.Ret.APIClient
import com.elfalt.tmdb.Ret.ApiInterface
import com.elfalt.tmdb.Ret.TvResponseDetails
import com.elfalt.tmdb.TvShowAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tv_details.*
import retrofit2.Call
import retrofit2.Response

class TvDetailsActivity : AppCompatActivity() {

    lateinit var tvId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_details)

        tvId = intent.getStringExtra("id")

        val apiInterface = APIClient.getRetrofit().create(ApiInterface::class.java)
        val call = apiInterface.getTvShowDetails(tvId, MoviesActivity.api_key)

        call.enqueue(object : retrofit2.Callback<TvResponseDetails> {

            override fun onResponse(
                call: Call<TvResponseDetails>,
                response: Response<TvResponseDetails>
            ) {

                if(response.isSuccessful){

                    val res = response.body()!!
                    Picasso.get().load(TvShowAdapter.BASE_IMAGE_URL + res.backdrop_path)
                        .into(tv_backdrop_path)

                    Picasso.get().load(TvShowAdapter.BASE_IMAGE_URL + res.poster_path)
                        .into(tv_poster_path)

                    tv_name.text = res.original_name
                    overview_detail.text = res.overview
                    statusDetails.text = res.status
                    first_air_date_detail.text = res.first_air_date
                    vote_average_detail.text = res.vote_average.toString()

                }
            }

            override fun onFailure(call: Call<TvResponseDetails>, t: Throwable) {

                Log.e("Failure",t.message)
            }

        })
    }
}
