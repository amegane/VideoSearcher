package com.amegane3231.videosearcher.repository

import com.amegane3231.videosearcher.api.YoutubeApi
import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData
import com.amegane3231.videosearcher.database.SearchHistoryDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VideoSearchRepositoryImpl : VideoSearchRepository, KoinComponent {
    private val youtubeAPI: YoutubeApi by inject()

    private val searchHistoryDatabase: SearchHistoryDatabase by inject()

    override suspend fun searchYoutube(query: String, pageToken: String) = youtubeAPI.searchData(query, pageToken)

    override suspend fun getYoutubeVideoData(videoId: String): YoutubeSearchedData = youtubeAPI.getVideoData(videoId)

    override suspend fun insertHistory(history: SearchHistory) = searchHistoryDatabase.insert(history)

    override suspend fun searchHistory(query: String) = searchHistoryDatabase.search(query)

    override suspend fun searchAllHistories() = searchHistoryDatabase.searchAll()

    override suspend fun deleteHistory(history: SearchHistory) = searchHistoryDatabase.delete(history)

    override suspend fun deleteAll() = searchHistoryDatabase.deleteAll()
}
