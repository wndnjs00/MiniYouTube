package com.example.miniyoutube.data.model.remote.searchvideo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Snippet(
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("channelId")
    val channelId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails,
    @SerializedName("channelTitle")
    val channelTitle: String,
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("liveBroadcastContent")
    val liveBroadcastContent: String,
    @SerializedName("defaultLanguage")
    val defaultLanguage: String,
    @SerializedName("localized")
    val localized: Localized,
    @SerializedName("tags")
    val tags: List<String>
) : Parcelable