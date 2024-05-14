package com.example.miniyoutube.ui.videodetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.miniyoutube.databinding.ActivityVideoDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideoDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityVideoDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}