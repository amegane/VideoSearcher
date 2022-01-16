package com.amegane3231.moviesearch.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeVideoId(
    val kind: String,
    val videoId: String,
    val channelId: String = "",
    val playlistId: String = ""
)