package com.example.miniyoutube.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("storage")
data class StorageEntity(
    @PrimaryKey
    val videoId: String,
    val channelId: String?,
    val title: String?,
    val description: String?,
    val url: String?
)

