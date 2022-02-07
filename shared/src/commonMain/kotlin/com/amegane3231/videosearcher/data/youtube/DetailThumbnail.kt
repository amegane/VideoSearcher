package com.amegane3231.videosearcher.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class DetailThumbnail(
    val url: String,
    val width: Int,
    val height: Int
)
