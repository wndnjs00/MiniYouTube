package com.example.miniyoutube.data.di

import com.example.miniyoutube.data.repository.YoutubeRepository
import com.example.miniyoutube.data.repository.YoutubeRepositoryImpl
import com.example.miniyoutube.data.service.YoutubeApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class YouTubeRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindYouTubeRepository(youtubeRepositoryImpl: YoutubeRepositoryImpl): YoutubeRepository

}