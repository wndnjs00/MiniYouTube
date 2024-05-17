package com.example.miniyoutube.ui.search

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

    fun getSearch(query: String, videoTypeId: String) = viewModelScope.launch {
        val result = repository.requestSearch(query, videoTypeId)

        result.let {
            _search.value = it
        }
    }

}