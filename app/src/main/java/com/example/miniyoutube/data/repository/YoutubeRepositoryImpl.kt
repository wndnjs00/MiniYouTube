package com.example.miniyoutube.data.repository

import com.example.miniyoutube.data.service.YoutubeApiService
import com.example.miniyoutube.data.model.remote.YoutubeVideo
import javax.inject.Inject

class YoutubeRepositoryImpl @Inject constructor(
    private val youtubeApiService: YoutubeApiService
) : YoutubeRepository {
    override suspend fun requestSearch(q: String, videoCategoryId: String): YoutubeVideo {
        return youtubeApiService.requestSearch(query = q, videoOrder = "relevance", videoType = "video", maxResults = 10, videoCategoryId = videoCategoryId, part = "snippet")
    }

    override suspend fun requestVideo(typeId: String): YoutubeVideo {
        return youtubeApiService.requestVideo(maxResults = 50, part = "snippet", videoCategoryId = typeId)
    }

}