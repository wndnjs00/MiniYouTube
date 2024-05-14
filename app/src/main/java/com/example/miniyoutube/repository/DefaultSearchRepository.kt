package com.example.miniyoutube.repository

import com.example.miniyoutube.data.YoutubeApiService
import com.example.miniyoutube.data.YoutubeVideo
import retrofit2.Response

class DefaultSearchRepository(
    private val youtubeApiService: YoutubeApiService
) : SearchRepository {
    override fun searchApi(q: String, videoCategoryId: String): Response<YoutubeVideo> {
        return youtubeApiService.searchApiService(query = q, videoOrder = "relevance", maxResults = 50, videoCategoryId = videoCategoryId)
    }

    override fun productApi(q: String): Response<YoutubeVideo> {
        return youtubeApiService.videoApiService(videoId = q, maxResults = 50)
    }

}