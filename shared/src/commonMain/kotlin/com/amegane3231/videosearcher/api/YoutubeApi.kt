package com.amegane3231.videosearcher.api

import com.amegane3231.videosearcher.data.video.youtube.YoutubeSearchedData
import com.amegane3231.videosearcher.data.video.youtube.YoutubeVideoDetailData

interface YoutubeApi {
    suspend fun searchData(query: String, pageToken: String): YoutubeSearchedData

    suspend fun getVideoData(videoId: String): YoutubeVideoDetailData
}
