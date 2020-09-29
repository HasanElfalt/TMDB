package com.elfalt.tmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.elfalt.tmdb.AppConstants
import com.elfalt.tmdb.adapters.MoviesAdapter
import com.elfalt.tmdb.R
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    private lateinit var moviesViewModel : MovieViewModel
    private var tTemp:String = "popular"
    private var tbool : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        moviesViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        movieRecyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)

        getRecyclerViewData(R.id.popular_tab)

        popular_tab.setOnClickListener {
            getRecyclerViewData(R.id.popular_tab)
        }

        top_Rated_tab.setOnClickListener { getRecyclerViewData(R.id.top_Rated_tab) }

        Now_Playing_tab.setOnClickListener {  getRecyclerViewData(R.id.Now_Playing_tab) }


    }
    private fun getRecyclerViewData(id : Int){

        val type : String = when(id){

            R.id.popular_tab ->  AppConstants.POPULAR

            R.id.top_Rated_tab -> AppConstants.TOP_RATED

            else -> AppConstants.NOW_PLAYING
        }

        if(type != tTemp) {
            tbool = 1
            tTemp = type
        }
        moviesViewModel.getMovies(type,tbool).observe(this, Observer {
            populateMovieRecycler(it)
        })
    }

    private fun populateMovieRecycler(moviesList: List<Movies>) {

        movieRecyclerView.adapter =
            MoviesAdapter(moviesList)

    }

}
