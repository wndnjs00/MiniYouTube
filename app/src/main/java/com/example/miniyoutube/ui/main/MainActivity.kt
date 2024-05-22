package com.example.miniyoutube.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.miniyoutube.R
import com.example.miniyoutube.databinding.ActivityMainBinding
import com.example.miniyoutube.ui.home.HomeFragment
import com.example.miniyoutube.ui.myvideo.MyVideoFragment
import com.example.miniyoutube.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        initBottomNavigation()
        setContentView(binding.root)
    }


    private fun initBottomNavigation() {
        setFragment(HomeFragment())

        binding.mainBottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    setFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.search -> {
                    setFragment(SearchFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.my_videos -> {
                    setFragment(MyVideoFragment())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }


    private fun setFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container_view, fragment)
            .commit()
    }

}