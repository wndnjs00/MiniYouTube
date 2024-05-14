package com.example.miniyoutube.ui.myvideo.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.miniyoutube.ui.myvideo.FakeMyVideoData

class MyVideoDiffUtil : DiffUtil.ItemCallback<FakeMyVideoData>() {

    override fun areItemsTheSame(oldItem: FakeMyVideoData, newItem: FakeMyVideoData): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: FakeMyVideoData, newItem: FakeMyVideoData): Boolean =
        oldItem == newItem
}
