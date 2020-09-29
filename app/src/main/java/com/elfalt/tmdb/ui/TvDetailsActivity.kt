package com.elfalt.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elfalt.tmdb.R
import com.elfalt.tmdb.adapters.TvShowAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tv_details.*


class TvDetailsActivity : AppCompatActivity() {

    private lateinit var tvId: String
    private lateinit var viewModel : TvDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_details)

        tvId = intent.getStringExtra("id").toString()

        viewModel = ViewModelProvider(this).get(TvDetailsViewModel::class.java)
        viewModel.getTvShowDetails(tvId).observe(this, Observer {


            Picasso.get().load(TvShowAdapter.BASE_IMAGE_URL + it.backdrop_path)
                .into(tv_backdrop_path)

            Picasso.get().load(TvShowAdapter.BASE_IMAGE_URL + it.poster_path)
                .into(tv_poster_path)

            tv_name.text = it.original_name
            overview_detail.text = it.overview
            statusDetails.text = it.status
            first_air_date_detail.text = it.first_air_date
            vote_average_detail.text = it.vote_average.toString()
        })

    }
}
