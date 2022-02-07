package com.amegane3231.videosearcher.data.search

import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData

sealed class SearchResult {
    class YoutubeData(val data: YoutubeSearchedData) : SearchResult()
}
