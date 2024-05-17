package com.example.miniyoutube.ui.videodetail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.miniyoutube.R
import com.example.miniyoutube.data.model.local.StorageEntity
import com.example.miniyoutube.data.model.remote.Snippet
import com.example.miniyoutube.databinding.ActivityVideoDetailBinding
import com.example.miniyoutube.ui.model.FavoriteItem
import com.example.miniyoutube.util.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideoDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityVideoDetailBinding.inflate(layoutInflater) }

    private val roomViewModel by viewModels<RoomViewModel>()

    private lateinit var favoriteItem: FavoriteItem


    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                this.isEnabled = false
                onBackPressedDispatcher.onBackPressed()

                if (Build.VERSION.SDK_INT >= 34) {
                    overrideActivityTransition(
                        Activity.OVERRIDE_TRANSITION_CLOSE,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                } else {
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getIntentData()
        btnShare()
        saveLikes(
            StorageEntity(
                videoId = favoriteItem.videoId,
                channelId = favoriteItem.channelId,
                title = favoriteItem.title,
                description = favoriteItem.description,
                url = favoriteItem.url
            )
        )
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        setContentView(binding.root)
    }


    private fun getIntentData() {
        // 보낸 데이터 받아오기
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.FAVORITE_ITEM_KEY, FavoriteItem::class.java)
                ?.let { favorite ->
                    favoriteItem = favorite
                    Log.d("favoriteItem", favoriteItem.toString())
                }
        } else {
            intent.getParcelableExtra<FavoriteItem>(Constants.FAVORITE_ITEM_KEY)?.let { favorite ->
                favoriteItem = favorite
            }
        }


        with(binding) {
            Glide.with(this@VideoDetailActivity)
                .load(favoriteItem.url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(detailCardViewIv)

            detailVideoTitle.text = favoriteItem.title
            detailVideoContent.text = favoriteItem.description
        }

    }

    private fun saveLikes(storageEntity: StorageEntity) {

        binding.detailLikeBtn.setOnClickListener {

            // viewModel을 통해 좋아요한 아이템을 저장
            roomViewModel.saveLikeData(storageEntity)
            Log.d("데이터 저장", roomViewModel.saveLikeData(storageEntity).toString())
            Snackbar.make(binding.detailVideoContent, "My Video에 추가되었습니다", Snackbar.LENGTH_SHORT)
                .show()
        }

    }

    private fun btnShare() {
        // 공유버튼 눌렀을때
        binding.detailShareBtn.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=${favoriteItem.videoId}")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, null))
        }
    }

}