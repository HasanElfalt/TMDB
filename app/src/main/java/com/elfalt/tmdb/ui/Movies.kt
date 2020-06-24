package com.elfalt.tmdb.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movies (
    @PrimaryKey
    val id : Int,
    val original_title :String,
    val release_date :String,
    val poster_path  :String,
    val vote_average : Float
)