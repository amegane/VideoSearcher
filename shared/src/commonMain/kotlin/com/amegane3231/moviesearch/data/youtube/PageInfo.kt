package com.amegane3231.moviesearch.data.youtube

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)