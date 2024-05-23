package com.example.miniyoutube.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniyoutube.data.model.remote.videoinfo.YoutubeVideoInfo
import com.example.miniyoutube.data.repository.YoutubeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) : ViewModel() {


    private val _mostPopularVideo = MutableLiveData<YoutubeVideoInfo>()
    val mostPopularVideo: LiveData<YoutubeVideoInfo>
        get() = _mostPopularVideo

    private val _categoryVideo = MutableLiveData<YoutubeVideoInfo>()
    val categoryVideo: LiveData<YoutubeVideoInfo>
        get() = _categoryVideo


    fun requestVideo(videoCategoryId: String, selectVideo: SelectVideo) {
        when (selectVideo) {
            SelectVideo.HEADING -> {
                viewModelScope.launch {
                    val resultYoutubeVideo = youtubeRepository.requestVideo(videoCategoryId)
                    _mostPopularVideo.value = resultYoutubeVideo
                }
            }

            SelectVideo.CATEGORY -> {
                viewModelScope.launch {
                    val resultYoutubeVideo = youtubeRepository.requestVideo(videoCategoryId)
                    _categoryVideo.value = resultYoutubeVideo
                }
            }
        }
    }
}