package com.example.miniyoutube

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miniyoutube.databinding.ActivityMainBinding
import com.example.miniyoutube.databinding.ActivityVideoDetailBinding

class VideoDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityVideoDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}