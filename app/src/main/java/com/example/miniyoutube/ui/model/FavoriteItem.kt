package com.example.miniyoutube.ui.model

import android.os.Parcelable
import com.example.miniyoutube.data.model.local.StorageEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteItem(
    val videoId: String,
    val channelId: String?,
    val title: String?,
    val description: String?,
    val url: String?
):Parcelable

fun StorageEntity.toItem() = FavoriteItem(
    videoId = videoId,
    channelId = channelId,
    title = title,
    description = description,
    url = url
)


