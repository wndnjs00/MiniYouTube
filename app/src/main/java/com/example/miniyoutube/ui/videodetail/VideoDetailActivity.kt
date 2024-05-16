package com.example.miniyoutube.ui.videodetail

import android.os.Build
import android.os.Bundle
import android.util.Log
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getIntentData()
        saveLikes(StorageEntity(videoId = "", channelId = "", title = "타이틀", description = "설명", url = ""))
        setContentView(binding.root)
    }


    private fun getIntentData(){
        // 보낸 데이터 받아오기
        val youtubeData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.FAVORITE_ITEM_KEY, FavoriteItem::class.java)
        } else {
            intent.getParcelableExtra(Constants.FAVORITE_ITEM_KEY) as? FavoriteItem
        }

        with(binding){
            Glide.with(this@VideoDetailActivity)
                .load(youtubeData?.url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(detailCardViewIv)

            detailVideoTitle.text = youtubeData?.title
            detailVideoContent.text = youtubeData?.description
        }

    }

    private fun saveLikes(storageEntity : StorageEntity){

        binding.detailLikeBtn.setOnClickListener {

            // viewModel을 통해 좋아요한 아이템을 저장
            roomViewModel.saveLikeData(storageEntity)
            Log.d("데이터 저장", roomViewModel.saveLikeData(storageEntity).toString())
            Snackbar.make(binding.detailVideoContent, "좋아요가 추가되었습니다", Snackbar.LENGTH_SHORT).show()
        }

    }
}