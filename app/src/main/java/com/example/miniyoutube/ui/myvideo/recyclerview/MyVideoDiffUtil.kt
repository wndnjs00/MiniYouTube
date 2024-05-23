package com.example.miniyoutube.ui.myvideo.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.miniyoutube.ui.model.FavoriteItem

class MyVideoDiffUtil : DiffUtil.ItemCallback<FavoriteItem>() {

    override fun areItemsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean =
        oldItem == newItem
}
