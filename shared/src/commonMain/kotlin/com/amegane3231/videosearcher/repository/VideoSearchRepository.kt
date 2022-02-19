package com.amegane3231.videosearcher.repository

import com.amegane3231.videosearcher.api.YoutubeAPI
import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData
import com.amegane3231.videosearcher.database.SearchHistoryDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VideoSearchRepository : KoinComponent {
    private val youtubeAPI: YoutubeAPI by inject()

    private val searchHistoryDatabase: SearchHistoryDatabase by inject()

    suspend fun searchYoutube(query: String): YoutubeSearchedData = youtubeAPI.searchData(query)

    suspend fun insertHistory(history: SearchHistory) = searchHistoryDatabase.insert(history)

    suspend fun findHistory(query: String) = searchHistoryDatabase.search(query)

    suspend fun findAllHistories() = searchHistoryDatabase.searchAll()

    suspend fun deleteHistory(history: SearchHistory) = searchHistoryDatabase.delete(history)

    suspend fun deleteAll() = searchHistoryDatabase.deleteAll()
}
