package com.amegane3231.videosearcher.data.video.youtube

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    val default: DetailThumbnail,
    val medium: DetailThumbnail,
    val high: DetailThumbnail
)
