package com.amegane3231.videosearcher.api

import com.amegane3231.videosearcher.BuildConfig
import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.component.KoinComponent

class YoutubeApiImpl : YoutubeApi, KoinComponent {
    private val apiClient = APIClient()

    override suspend fun searchData(query: String, pageToken: String) =
        apiClient.client.get<YoutubeSearchedData>("https://www.googleapis.com/youtube/v3/search?key=${BuildConfig.youtubeApiKey}&type=video&&part=snippet&&maxResults=10&pageToken=$pageToken&q=$query") {
            contentType(ContentType.Application.Json)
        }

    override suspend fun getVideoData(videoId: String) =
        apiClient.client.get<YoutubeSearchedData>("https://www.googleapis.com/youtube/v3/videos?key=${BuildConfig.youtubeApiKey}&part=snippet&id=$videoId") {
            contentType(ContentType.Application.Json)
        }
}
