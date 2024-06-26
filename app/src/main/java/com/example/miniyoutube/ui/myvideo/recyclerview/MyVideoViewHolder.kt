package com.example.miniyoutube.ui.myvideo.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.R
import com.example.miniyoutube.databinding.ItemFavoriteVideoBinding
import com.example.miniyoutube.ui.model.FavoriteItem

class MyVideoViewHolder(
    parent: ViewGroup,
    private val onMyVideoClick: (favoriteItme: FavoriteItem) -> Unit,
    private val onMyVideoLongClick: (favoriteItme: FavoriteItem) -> Boolean,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_video, parent, false)
) {

    private val binding = ItemFavoriteVideoBinding.bind(itemView)


    fun bind(
        favoriteItme: FavoriteItem
    ) {

        Glide.with(itemView.context)
            .load(favoriteItme.url)
            .centerCrop()
            .into(binding.ivItem)
        binding.tvContentItem.text = favoriteItme.title

        binding.root.setOnClickListener {
            onMyVideoClick(favoriteItme)
        }
        binding.root.setOnLongClickListener {
            onMyVideoLongClick(favoriteItme)
        }
    }
}
