package com.amegane3231.moviesearch.repository

import com.amegane3231.moviesearch.api.YoutubeAPI
import com.amegane3231.moviesearch.data.youtube.YoutubeSearchedData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VideoSearchRepository : KoinComponent {
    private val youtubeAPI: YoutubeAPI by inject()

    suspend fun searchYoutube(query: String): YoutubeSearchedData = youtubeAPI.searchData(query)
}