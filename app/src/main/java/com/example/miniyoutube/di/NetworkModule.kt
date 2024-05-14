package com.example.miniyoutube.di

import com.example.miniyoutube.data.YoutubeApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun okhttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient())
            .build()
    }

    fun provideYoutubeApiService(retrofit: Retrofit) : YoutubeApiService = retrofit.create(YoutubeApiService::class.java)
}