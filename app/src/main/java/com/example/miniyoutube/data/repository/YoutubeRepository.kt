package com.example.miniyoutube.data.repository

import com.example.miniyoutube.data.model.remote.searchvideo.YoutubeVideo
import com.example.miniyoutube.data.model.remote.videoinfo.YoutubeVideoInfo

interface YoutubeRepository {
    suspend fun requestSearch(q: String, videoCategoryId : String) : YoutubeVideo

    suspend fun requestSearchMore(q: String, videoCategoryId: String, pageToken: String) : YoutubeVideo

    suspend fun requestVideo(videoCategoryId:String) : YoutubeVideoInfo
}