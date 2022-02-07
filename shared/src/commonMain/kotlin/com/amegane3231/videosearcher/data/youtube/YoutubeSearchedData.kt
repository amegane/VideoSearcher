package com.amegane3231.videosearcher.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeSearchedData(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val prevPageToken: String = "",
    val pageInfo: PageInfo,
    val items: List<YoutubeVideoResource>
)
