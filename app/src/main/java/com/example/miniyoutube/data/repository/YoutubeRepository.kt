package com.example.miniyoutube.data.repository

import com.example.miniyoutube.data.model.remote.YoutubeVideo

interface YoutubeRepository {
    suspend fun requestSearch(q: String, videoCategoryId : String) : YoutubeVideo

    suspend fun requestVideo(typeId: String) : YoutubeVideo
}