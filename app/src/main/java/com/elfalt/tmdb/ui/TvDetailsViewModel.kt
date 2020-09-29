package com.elfalt.tmdb.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.elfalt.tmdb.Repositories.RepositoryData
import com.elfalt.tmdb.Ret.TvResponseDetails

class TvDetailsViewModel(application: Application) : AndroidViewModel(application){

    fun getTvShowDetails(tvId : String): LiveData<TvResponseDetails> {
        return RepositoryData.getTvShowsDetails(tvId)
    }
}