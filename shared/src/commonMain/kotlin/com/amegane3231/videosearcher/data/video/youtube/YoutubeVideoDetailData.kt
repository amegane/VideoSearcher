package com.amegane3231.videosearcher.data.video.youtube

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeVideoDetailData(
    val kind: String,
    val etag: String,
    val nextPageToken: String = "",
    val prevPageToken: String = "",
    val pageInfo: PageInfo,
    val items: List<YoutubeVideoDetailResource>
)
