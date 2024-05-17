package com.example.miniyoutube.data.model.remote.searchvideo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Localized(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String
) : Parcelable