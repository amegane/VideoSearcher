package com.amegane3231.videosearcher.database

import com.amegane3231.videosearcher.data.search.SearchHistory

interface SearchHistoryDatabase {
    suspend fun insert(history: SearchHistory)

    suspend fun search(query: String): List<SearchHistory>

    suspend fun searchAll(): List<SearchHistory>

    suspend fun delete(history: SearchHistory)

    suspend fun deleteAll()
}
