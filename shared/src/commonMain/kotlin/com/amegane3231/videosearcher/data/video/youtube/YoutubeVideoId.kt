package com.amegane3231.videosearcher.data.video.youtube

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeVideoId(
    val kind: String,
    val videoId: String,
    val channelId: String = "",
    val playlistId: String = ""
)
