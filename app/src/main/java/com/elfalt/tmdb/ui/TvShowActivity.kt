package com.elfalt.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elfalt.tmdb.R
import com.elfalt.tmdb.Ret.*
import com.elfalt.tmdb.TvShowAdapter
import kotlinx.android.synthetic.main.activity_tv_show.*
import retrofit2.Call
import retrofit2.Response

class TvShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show)

        getRecyclerViewData(POPULAR)

        getRecyclerViewData(TOP_RATED)

        getRecyclerViewData(ON_THE_AIR)

        getRecyclerViewData(AIRING_TODAY)

    }



    private fun getRecyclerViewData(type : String){

        val apiInterface = APIClient.getRetrofit().create(ApiInterface::class.java)
        val call : Call<TvResponse> = apiInterface.getTvShow(type, MoviesActivity.api_key)

        call.enqueue(object : retrofit2.Callback<TvResponse> {

            override fun onResponse(
                call: Call<TvResponse>,
                response: Response<TvResponse>
            ) {

                if(response.isSuccessful){
                    val tvShowList = response.body()!!.results

                    if(type == POPULAR)   populateTvShowRecycler(tvShowList, popular_recycler)
                    else if (type == TOP_RATED)   populateTvShowRecycler(tvShowList, top_rated_recycler)
                    else if (type == ON_THE_AIR)   populateTvShowRecycler(tvShowList, on_the_air_recycler)
                    else if (type == AIRING_TODAY)   populateTvShowRecycler(tvShowList, airing_today_recycler)
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {

                Log.e("Failure",t.message)
            }

        })
    }

    private fun populateTvShowRecycler(tvShowList: List<TvShow>, recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = TvShowAdapter(tvShowList)

    }
    companion object{
        const val POPULAR     = "popular"
        const val TOP_RATED   = "top_rated"
        const val ON_THE_AIR  = "on_the_air"
        const val AIRING_TODAY= "airing_today"
    }

}
