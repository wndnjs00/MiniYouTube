package com.example.miniyoutube.ui.myvideo.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.R
import com.example.miniyoutube.databinding.ItemFavoriteVideoBinding
import com.example.miniyoutube.ui.myvideo.FakeMyVideoData

class MyVideoViewHolder(
    parent: ViewGroup,
    private val onMyVideoClick: (fakeMyVideoData: FakeMyVideoData) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_video, parent, false)
) {

    private val binding = ItemFavoriteVideoBinding.bind(itemView)



    fun bind(
        fakeMyVideoData: FakeMyVideoData
    ) {

        Glide.with(itemView.context)
            .load(R.drawable.ic_launcher_background)
            .fitCenter()
            .into(binding.ivItem)
        binding.tvContentItem.text = fakeMyVideoData.title

        binding.root.setOnClickListener {
            onMyVideoClick(fakeMyVideoData)
        }
    }
}
