package com.example.miniyoutube.data.repository

import com.example.miniyoutube.data.model.remote.YoutubeVideo
import com.example.miniyoutube.data.model.remote.YoutubeVideoInfo

interface YoutubeRepository {
    suspend fun requestSearch(q: String, videoCategoryId : String) : YoutubeVideo

    suspend fun requestVideo(videoCategoryId:String) : YoutubeVideoInfo
}