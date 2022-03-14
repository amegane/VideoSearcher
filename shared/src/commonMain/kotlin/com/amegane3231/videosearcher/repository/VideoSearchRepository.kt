package com.amegane3231.videosearcher.repository

import com.amegane3231.videosearcher.data.history.SearchHistory
import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData
import com.amegane3231.videosearcher.data.youtube.YoutubeVideoDetailData

interface VideoSearchRepository {
    suspend fun searchYoutube(query: String, pageToken: String): YoutubeSearchedData

    suspend fun getYoutubeVideoData(videoId: String): YoutubeVideoDetailData

    suspend fun insertHistory(history: SearchHistory)

    suspend fun searchHistory(query: String): List<SearchHistory>

    suspend fun searchAllHistories(): List<SearchHistory>

    suspend fun deleteHistory(history: SearchHistory)

    suspend fun deleteAll()
}
