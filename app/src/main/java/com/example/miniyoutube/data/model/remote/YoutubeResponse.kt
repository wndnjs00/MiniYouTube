package com.example.miniyoutube.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class YoutubeVideo(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: List<Item>
) : Parcelable

@Parcelize
data class Item(
    @SerializedName("id")
    val id : Id,
    @SerializedName("snippet")
    val snippet: Snippet,
    @SerializedName("contentDetails")
    val contentDetails : ContentDetails
) : Parcelable

@Parcelize
data class ContentDetails(
    @SerializedName("duration")
    val duration: String
) : Parcelable

@Parcelize
data class Id(
    @SerializedName("kind")
    val kind : String,
    @SerializedName("videoId")
    val videoId: String
) : Parcelable

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
    val localized : Localized,
    @SerializedName("tags")
    val tags: List<String>
) : Parcelable

@Parcelize
data class Localized(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String
): Parcelable
@Parcelize
data class Thumbnails(
    @SerializedName("medium")
    val medium : Medium
) : Parcelable

@Parcelize
data class Medium(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
): Parcelable