package com.example.miniyoutube.ui.myvideo.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.miniyoutube.ui.model.FavoriteItem

class MyVideoAdapter(
    private val onMyVideoClick: (favoriteItme: FavoriteItem) -> Unit,
    private val onMyVideoLongClick: (favoriteItme: FavoriteItem) -> Boolean
) : ListAdapter<FavoriteItem, MyVideoViewHolder>(MyVideoDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyVideoViewHolder = MyVideoViewHolder(parent = parent, onMyVideoClick = onMyVideoClick, onMyVideoLongClick = onMyVideoLongClick)

    override fun onBindViewHolder(holder: MyVideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}