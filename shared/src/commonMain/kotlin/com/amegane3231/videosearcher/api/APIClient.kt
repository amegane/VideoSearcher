package com.amegane3231.videosearcher.api

import io.ktor.client.*

expect class APIClient {
    val client: HttpClient
}
