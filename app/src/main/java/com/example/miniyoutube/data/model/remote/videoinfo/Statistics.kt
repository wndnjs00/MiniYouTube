package com.example.miniyoutube.data.model.remote.videoinfo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Statistics(
    @SerializedName("viewCount")
    val viewCount: String? = ""
) : Parcelable