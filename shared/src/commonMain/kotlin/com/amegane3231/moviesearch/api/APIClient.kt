package com.amegane3231.moviesearch.api

import io.ktor.client.*

expect class APIClient {
    val client: HttpClient
}