package com.example.miniyoutube.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniyoutube.data.model.remote.searchvideo.YoutubeVideo
import com.example.miniyoutube.data.repository.YoutubeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: YoutubeRepository
) : ViewModel() {

    private val _search = MutableLiveData<YoutubeVideo>()
    val search : LiveData<YoutubeVideo> = _search

    private lateinit var change : String

    fun getSearch(query: String, videoTypeId: String) = viewModelScope.launch {
        val result = repository.requestSearch(query, videoTypeId)
        change = result.nextPageToken


        result.let {
            _search.value = it
        }
    }

    private val _more = MutableLiveData<YoutubeVideo>()
    val more : LiveData<YoutubeVideo> = _more

    fun getSearchMore(query: String, videoTypeId: String) = viewModelScope.launch {
        val resultMore = repository.requestSearchMore(query, videoTypeId, change)

        resultMore.let {
            _more.value = it
        }
    }

}