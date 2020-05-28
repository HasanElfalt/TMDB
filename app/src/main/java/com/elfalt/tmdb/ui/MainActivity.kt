package com.elfalt.tmdb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elfalt.tmdb.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Movies.setOnClickListener {
            val moviesIntent = Intent(this, MoviesActivity:: class.java)
            startActivity(moviesIntent)
        }
        tv_show.setOnClickListener {
            val tvShowIntent = Intent(this, TvShowActivity:: class.java)
            startActivity(tvShowIntent)
        }
    }

}
