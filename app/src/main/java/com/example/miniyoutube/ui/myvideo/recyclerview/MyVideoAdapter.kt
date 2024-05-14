package com.example.miniyoutube.ui.myvideo.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.miniyoutube.ui.myvideo.FakeMyVideoData

class MyVideoAdapter(
    private val onMyVideoClick: (fakeMyVideoData: FakeMyVideoData) -> Unit,
) : ListAdapter<FakeMyVideoData, MyVideoViewHolder>(MyVideoDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyVideoViewHolder = MyVideoViewHolder(parent = parent, onMyVideoClick = onMyVideoClick)

    override fun onBindViewHolder(holder: MyVideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}