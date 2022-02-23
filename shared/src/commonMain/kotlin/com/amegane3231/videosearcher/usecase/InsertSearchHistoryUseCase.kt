package com.amegane3231.videosearcher.usecase

interface InsertSearchHistoryUseCase {
    suspend operator fun invoke(query: String)
}
