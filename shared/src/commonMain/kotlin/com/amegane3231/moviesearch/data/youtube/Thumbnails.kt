package com.amegane3231.moviesearch.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    val default: DetailThumbnail,
    val medium: DetailThumbnail,
    val high: DetailThumbnail
)