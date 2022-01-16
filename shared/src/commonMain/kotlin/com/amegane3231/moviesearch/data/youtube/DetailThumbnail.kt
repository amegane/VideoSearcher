package com.amegane3231.moviesearch.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class DetailThumbnail(
    val url: String,
    val width: Int,
    val height: Int
)