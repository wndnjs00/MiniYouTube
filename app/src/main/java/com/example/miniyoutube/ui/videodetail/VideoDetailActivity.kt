package com.example.miniyoutube.ui.videodetail

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
import com.example.miniyoutube.databinding.ActivityVideoDetailBinding
import com.example.miniyoutube.ui.model.FavoriteItem
import com.example.miniyoutube.util.Constants
import com.google.android.material.snackbar.Snackbar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideoDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityVideoDetailBinding.inflate(layoutInflater) }

    private val videoDetailViewModel by viewModels<VideoDetailViewModel>()

    private lateinit var favoriteItem: FavoriteItem

    private var isLiked = false


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
        setContentView(binding.root)

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
        checkVideoLiked()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        youtubeVideo()
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
//            개인학습관련으로 남겨놓음
//            Glide.with(this@VideoDetailActivity)
//                .load(favoriteItem.url)
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .into(detailCardViewIv)

            detailVideoTitle.text = favoriteItem.title
            detailVideoContent.text = favoriteItem.description
        }

    }


    private fun saveLikes(storageEntity: StorageEntity) {

        binding.detailLikeBtn.setOnClickListener {

            // isLiked 뒤집기 (isLiked가 true면 false로, false면 ture로)
            isLiked = !isLiked

            if (isLiked){
                // Room 사용해서 좋아요 데이터 저장
                videoDetailViewModel.saveLikeData(storageEntity)
                Snackbar.make(binding.detailVideoContent, "My Video에 추가되었습니다", Snackbar.LENGTH_SHORT).show()
            }else{
                // Room 사용해서 좋아요 데이터삭제
                videoDetailViewModel.deleteLikeData(favoriteItem.videoId)
                Snackbar.make(binding.detailVideoContent, "My Video에서 삭제되었습니다", Snackbar.LENGTH_SHORT).show()
            }

            // 버튼 색상 업데이트
            updateLikeButton()
        }
    }


    private fun checkVideoLiked(){

        videoDetailViewModel.isVideoLiked(favoriteItem.videoId).observe(this){
            // LiveData에서 보낸값(좋아요 클릭했는지 안했는지)으로 isLiked값 업데이트
            isLiked = it
            Log.d("it_data__", it.toString())
            // 버튼 색상 업데이트
            updateLikeButton()
        }
    }

    private fun updateLikeButton() {
        if (isLiked) {
            binding.detailLikeBtn.setBackgroundResource(R.drawable.corner_button2)
        } else {
            binding.detailLikeBtn.setBackgroundResource(R.drawable.corner_button)
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


    // youtube video player 적용함수
    private fun youtubeVideo(){
        val youtubePlayerView : YouTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                val videoId = favoriteItem.videoId
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

    }

}