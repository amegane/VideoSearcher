package com.amegane3231.videosearcher.data.video.common

data class CommonVideoDetail(
    val videoId: String,
    val title: String,
    val imageUrl: String = "",
    val description: String = ""
)
