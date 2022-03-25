package com.amegane3231.videosearcher.data.video.youtube

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeVideoSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String
)
