package com.example.miniyoutube.ui.videodetail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.miniyoutube.R
import com.example.miniyoutube.data.model.local.SnippetEntity
import com.example.miniyoutube.data.model.remote.Snippet
import com.example.miniyoutube.databinding.ActivityVideoDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideoDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityVideoDetailBinding.inflate(layoutInflater) }

    companion object{
        const val EXTRA_YOUTUBE : String = "extra_youtube"
    }

    private val roomViewModel by viewModels<RoomViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getIntentData()
        saveLikes(SnippetEntity(videoId = "", channelId = "", title = "타이틀", description = "설명"))
        setContentView(binding.root)
    }


    private fun getIntentData(){
        // 보낸 데이터 받아오기
        val youtubeData = intent.getParcelableExtra<Snippet>(EXTRA_YOUTUBE)

        with(binding){
            Glide.with(this@VideoDetailActivity)
                .load(youtubeData?.thumbnails?.medium?.url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(detailCardViewIv)

            detailVideoTitle.text = youtubeData?.title
            detailVideoContent.text = youtubeData?.description
        }

    }

    private fun saveLikes(snippetEntity : SnippetEntity){

        binding.detailLikeBtn.setOnClickListener {

            // viewModel을 통해 좋아요한 아이템을 저장
            roomViewModel.saveLikeData(snippetEntity)
            Log.d("데이터 저장", roomViewModel.saveLikeData(snippetEntity).toString())
            Snackbar.make(binding.detailVideoContent, "좋아요가 추가되었습니다", Snackbar.LENGTH_SHORT).show()
        }

    }
}