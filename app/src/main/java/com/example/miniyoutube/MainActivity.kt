package com.example.miniyoutube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.miniyoutube.databinding.ActivityMainBinding
import com.example.miniyoutube.ui.myvideo.MyVideoFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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