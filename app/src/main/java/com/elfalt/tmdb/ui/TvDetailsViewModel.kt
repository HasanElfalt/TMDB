package com.elfalt.tmdb.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.elfalt.tmdb.remoteSource.TvResponseDetails
import com.elfalt.tmdb.repositories.RepositoryTvDetailsData

class TvDetailsViewModel(application: Application) : AndroidViewModel(application){

    fun getTvShowDetails(tvId : String): LiveData<TvResponseDetails> {
        return RepositoryTvDetailsData.getTvShowsDetails(tvId)
    }
}