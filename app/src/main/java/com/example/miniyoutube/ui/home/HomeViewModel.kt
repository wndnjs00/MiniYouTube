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

//    private val default = MutableLiveData<YoutubeVideoInfo>()
//    private val sport = MutableLiveData<YoutubeVideoInfo>()
//    private val movie = MutableLiveData<YoutubeVideoInfo>()
//    private val music = MutableLiveData<YoutubeVideoInfo>()
//    private val pet = MutableLiveData<YoutubeVideoInfo>()
//    private val drama = MutableLiveData<YoutubeVideoInfo>()
//
//    fun requestVideoPosition(videoCategoryId: String){
//        viewModelScope.launch {
//            val resultYoutubeVideo = youtubeRepository.requestVideo(videoCategoryId)
//            when(resultYoutubeVideo){
//
//            }
//        }
//    }

    private val _youtubeVideo = MutableLiveData<YoutubeVideoInfo>()
    val youtubeVideo: LiveData<YoutubeVideoInfo>
        get() = _youtubeVideo


    fun requestVideo(videoCategoryId: String) {
        viewModelScope.launch{//비동기 실행
            val resultYoutubeVideo = youtubeRepository.requestVideo(videoCategoryId)
            _youtubeVideo.value = resultYoutubeVideo
        }
    }
}