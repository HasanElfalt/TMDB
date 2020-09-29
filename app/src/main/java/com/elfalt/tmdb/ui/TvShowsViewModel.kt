package com.elfalt.tmdb.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.elfalt.tmdb.Repositories.RepositoryData
import com.elfalt.tmdb.Ret.TvShow

class TvShowsViewModel(application: Application) : AndroidViewModel(application) {

    fun getTvShows(tvType : String) : LiveData<List<TvShow>> {
        return RepositoryData.getTvShows(tvType)
    }
}