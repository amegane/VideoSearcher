package com.amegane3231.videosearcher.api

import com.amegane3231.videosearcher.BuildConfig
import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.core.component.KoinComponent

class YoutubeApiImpl : YoutubeApi, KoinComponent {
    private val apiClient = APIClient()

    override suspend fun searchData(query: String) =
        apiClient.client.get<YoutubeSearchedData>("https://www.googleapis.com/youtube/v3/search?key=${BuildConfig.youtubeApiKey}&type=video&&part=snippet&&q=$query") {
            contentType(ContentType.Application.Json)
        }
}
