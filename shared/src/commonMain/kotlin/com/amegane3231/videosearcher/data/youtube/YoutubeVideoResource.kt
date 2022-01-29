package com.amegane3231.videosearcher.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeVideoResource(
    val kind: String,
    val etag: String,
    val id: YoutubeVideoId,
    val snippet: YoutubeVideoSnippet
)