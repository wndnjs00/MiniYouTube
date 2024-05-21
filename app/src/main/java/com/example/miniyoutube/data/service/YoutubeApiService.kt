package com.example.miniyoutube.data.service

import com.example.miniyoutube.data.model.remote.searchvideo.YoutubeVideo
import com.example.miniyoutube.data.model.remote.videoinfo.YoutubeVideoInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {
    @GET("v3/search")
    suspend fun requestSearch(
        @Query("key") apiKey: String = "AIzaSyCJ25-6vtg3rknttOLaSSl1q6TDI_yfY7U",
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String,
        @Query("maxResults") maxResults: Int,
        @Query("videoCategoryId") videoCategoryId: String,
        @Query("part") part: String,
    ) : YoutubeVideo


    @GET("v3/videos")
    suspend fun requestVideo(
        @Query("key") apiKey: String = "AIzaSyCJ25-6vtg3rknttOLaSSl1q6TDI_yfY7U",
        @Query("videoCategoryId") videoCategoryId: String,
        @Query("maxResults") maxResults: Int,
        @Query("part") part: String,
        @Query("chart") chart: String
    ) : YoutubeVideoInfo
}