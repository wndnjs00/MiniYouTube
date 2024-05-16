package com.example.miniyoutube.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniyoutube.data.model.remote.YoutubeVideo
import com.example.miniyoutube.data.model.remote.YoutubeVideoInfo
import com.example.miniyoutube.data.repository.YoutubeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) : ViewModel() {

    private val _youtubeVideo = MutableLiveData<YoutubeVideoInfo>()

    val youtubeVideo: LiveData<YoutubeVideoInfo>
        get() = _youtubeVideo


    fun requestVideo(videoCategoryId: String) {
        viewModelScope.launch {
            val resultYoutubeVideo = youtubeRepository.requestVideo(videoCategoryId)

            _youtubeVideo.value = resultYoutubeVideo

        }
    }
}