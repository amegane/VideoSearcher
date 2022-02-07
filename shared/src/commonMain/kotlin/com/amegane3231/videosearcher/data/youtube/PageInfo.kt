package com.amegane3231.videosearcher.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)