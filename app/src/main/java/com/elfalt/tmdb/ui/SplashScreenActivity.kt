package com.elfalt.tmdb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elfalt.tmdb.AppConstants
import com.elfalt.tmdb.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread(){
            override fun run() {

                try {
                    sleep(AppConstants.SPLASH_TIMED_OUT)

                    finish()
                    val intent = Intent(baseContext,MainActivity::class.java)
                    startActivity(intent)

                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }


}
