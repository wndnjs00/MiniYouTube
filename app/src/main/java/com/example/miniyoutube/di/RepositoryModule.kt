package com.example.miniyoutube.di

import com.example.miniyoutube.data.YoutubeApiService
import com.example.miniyoutube.repository.DefaultSearchRepository
import com.example.miniyoutube.repository.SearchRepository

object RepositoryModule {
    fun provideRepository(
        youtubeApiService: YoutubeApiService
    ) : SearchRepository = DefaultSearchRepository(youtubeApiService)
}