package com.example.miniyoutube.SearchPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniyoutube.chipgroup.ChipType
import com.example.miniyoutube.data.YoutubeVideo
import com.example.miniyoutube.repository.SearchRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {

    private val _search = MutableLiveData<Response<YoutubeVideo>>()
    val search : LiveData<Response<YoutubeVideo>> = _search

    fun getSearch(query: String, videoTypeId: String) = viewModelScope.launch {
        val result = repository.searchApi(query, videoTypeId)

        result.let {
            _search.value = it
        }
    }

}