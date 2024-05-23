package com.example.miniyoutube.data.model.remote.videoinfo

import android.os.Parcelable
import com.example.miniyoutube.data.model.remote.searchvideo.ContentDetails
import com.example.miniyoutube.data.model.remote.searchvideo.Snippet
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class TrendItem(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: String,

    @SerializedName("snippet")
    val snippet: Snippet,

    @SerializedName("tags")
    val tags: List<String>,

    @SerializedName("contentDetails")
    val contentDetails: ContentDetails,

    @SerializedName("statistics")
    val statistics: Statistics
) : Parcelable