package com.example.miniyoutube

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MiniYouTubeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}