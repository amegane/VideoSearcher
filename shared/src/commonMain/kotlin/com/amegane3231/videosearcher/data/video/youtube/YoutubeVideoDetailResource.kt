package com.amegane3231.videosearcher.data.video.youtube

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeVideoDetailResource(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: YoutubeVideoSnippet
)
