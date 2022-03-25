package com.amegane3231.videosearcher.data.video.common

data class CommonVideoResource(
    val nextPageToken: String = "",
    val prevPageToken: String = "",
    val videoDetailList: List<CommonVideoDetail> = listOf()
)
