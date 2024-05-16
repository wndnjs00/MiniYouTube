package com.example.miniyoutube.data.service

import com.example.miniyoutube.data.model.remote.YoutubeVideo
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
        @Query("videoCategoryId") videoCategoryId: String,
        @Query("part") part: String
    ) : YoutubeVideo


    @GET("v3/videos")
    suspend fun requestVideo(
        @Query("key") apiKey: String = "AIzaSyAFcV1rTQEDQbG3OrEeWzMDuHjYM-Xd3ig",
        @Query("chart") chart : String = "mostPopular",
        @Query("maxResults") maxResults: Int = 10,
        @Query("videoCategoryId") videoCategoryId: String = "0",
        @Query("part") part: String,
    ) : YoutubeVideo
}