package com.example.miniyoutube.data

import com.example.miniyoutube.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {
    @GET("v3/search")
    fun searchApiService(
        @Query("key") apiKey: String = BuildConfig.GOOGLE_API_KEY,
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String = "video",
        @Query("maxResults") maxResults: Int,
        @Query("videoCategoryId") videoCategoryId: String,
        @Query("part") part: String = "snippet"
    ) : Response<YoutubeVideo>


    @GET("v3/videos")
    fun videoApiService(
        @Query("key") apiKey: String = BuildConfig.GOOGLE_API_KEY,
        @Query("id") videoId: String,
        @Query("maxResults") maxResults: Int,
        @Query("part") part: String = "contentDetails, statistics",
    ) : Response<YoutubeVideo>
}