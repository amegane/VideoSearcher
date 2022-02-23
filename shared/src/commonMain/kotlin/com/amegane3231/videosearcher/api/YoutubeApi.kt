package com.amegane3231.videosearcher.api

import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData

interface YoutubeApi {
    suspend fun searchData(query: String): YoutubeSearchedData
}
