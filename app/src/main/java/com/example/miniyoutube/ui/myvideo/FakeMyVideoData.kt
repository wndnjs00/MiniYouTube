package com.example.miniyoutube.ui.myvideo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FakeMyVideoData(
    val url : String?,
    val title: String?
): Parcelable
