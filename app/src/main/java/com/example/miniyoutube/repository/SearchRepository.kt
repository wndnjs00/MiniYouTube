package com.example.miniyoutube.repository

import com.example.miniyoutube.data.YoutubeVideo
import retrofit2.Response

interface SearchRepository {
    suspend fun searchApi(q: String, videoCategoryId : String) : Response<YoutubeVideo>

    suspend fun productApi(q: String) : Response<YoutubeVideo>
}