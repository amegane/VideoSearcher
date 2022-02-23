package com.amegane3231.videosearcher.repository

import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData

interface VideoSearchRepository {
    suspend fun searchYoutube(query: String): YoutubeSearchedData

    suspend fun insertHistory(history: SearchHistory)

    suspend fun searchHistory(query: String): List<SearchHistory>

    suspend fun searchAllHistories(): List<SearchHistory>

    suspend fun deleteHistory(history: SearchHistory)

    suspend fun deleteAll()
}
