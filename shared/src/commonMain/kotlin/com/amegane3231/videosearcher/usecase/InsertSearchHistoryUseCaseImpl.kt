package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.repository.VideoSearchRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InsertSearchHistoryUseCaseImpl : InsertSearchHistoryUseCase, KoinComponent {
    private val repository: VideoSearchRepository by inject()

    override suspend fun invoke(query: String) {
        repository.insertHistory(
            SearchHistory().apply {
                words = query
            }
        )
    }
}
