package com.example.miniyoutube.data.service

import com.example.miniyoutube.data.model.remote.YoutubeVideo
import com.example.miniyoutube.data.model.remote.YoutubeVideoInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {
    @GET("v3/search")
    suspend fun requestSearch(
        @Query("key") apiKey: String = "AIzaSyAFcV1rTQEDQbG3OrEeWzMDuHjYM-Xd3ig",
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String,
        @Query("maxResults") maxResults: Int,
        @Query("videoCategoryId") videoCategoryId: Int,
        @Query("part") part: String
    ) : YoutubeVideo


    @GET("v3/videos")
    suspend fun requestVideo(
        @Query("key") apiKey: String = "AIzaSyAFcV1rTQEDQbG3OrEeWzMDuHjYM-Xd3ig",
        @Query("videoCategoryId") videoCategoryId: String,
        @Query("maxResults") maxResults: Int,
        @Query("part") part: String,
        @Query("chart") chart: String
    ) : YoutubeVideoInfo
}