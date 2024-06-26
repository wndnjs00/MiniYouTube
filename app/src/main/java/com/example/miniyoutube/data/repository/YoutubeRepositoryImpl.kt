package com.example.miniyoutube.data.repository

import com.example.miniyoutube.data.service.YoutubeApiService
import com.example.miniyoutube.data.model.remote.searchvideo.YoutubeVideo
import com.example.miniyoutube.data.model.remote.videoinfo.YoutubeVideoInfo
import javax.inject.Inject

class YoutubeRepositoryImpl @Inject constructor(
    private val youtubeApiService: YoutubeApiService
) : YoutubeRepository {
    override suspend fun requestSearch(q: String, videoCategoryId: String): YoutubeVideo {
        return youtubeApiService.requestSearch(query = q, videoOrder = "relevance", videoType = "video", maxResults = 10, videoCategoryId = videoCategoryId, part = "snippet")
    }

    override suspend fun requestSearchMore(
        q: String,
        videoCategoryId: String,
        pageToken: String
    ): YoutubeVideo {
        return youtubeApiService.requestSearchMore(query = q, videoOrder = "relevance", videoType = "video", maxResults = 1, videoCategoryId = videoCategoryId, part = "snippet", pageToken = pageToken)
    }

    override suspend fun requestVideo(videoCategoryId:String): YoutubeVideoInfo { //고정된 값 _아래에서 고정
        return youtubeApiService.requestVideo(videoCategoryId = videoCategoryId, maxResults = 5, part = "snippet", chart = "mostPopular" )
    }
}