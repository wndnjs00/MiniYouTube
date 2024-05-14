package com.example.miniyoutube.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniyoutube.data.model.remote.YoutubeVideo
import com.example.miniyoutube.data.repository.YoutubeRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: YoutubeRepository
) : ViewModel() {

    private val _search = MutableLiveData<YoutubeVideo>()
    val search : LiveData<YoutubeVideo> = _search

    fun getSearch(query: String, videoTypeId: String) = viewModelScope.launch {
        val result = repository.requestSearch(query, videoTypeId)

        result.let {
            _search.value = it
        }
    }

}