package com.amegane3231.videosearcher.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeVideoDetailResource(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: YoutubeVideoSnippet
)
