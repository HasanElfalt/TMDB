package com.elfalt.tmdb.localSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elfalt.tmdb.ui.Movies

@Database(entities = [Movies::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMoviesDao() : MoviesDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "popular_movies_db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}