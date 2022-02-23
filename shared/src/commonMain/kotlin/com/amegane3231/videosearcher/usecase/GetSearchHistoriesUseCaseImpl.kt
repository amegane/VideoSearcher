package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.search.SearchHistory
import com.amegane3231.videosearcher.repository.VideoSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetSearchHistoriesUseCaseImpl : GetSearchHistoriesUseCase, KoinComponent {
    private val repository: VideoSearchRepository by inject()

    override suspend fun invoke(query: String): Flow<List<SearchHistory>> {
        return flow {
            if (query.isBlank()) {
                emit(repository.searchAllHistories())
            } else {
                emit(repository.searchHistory(query))
            }
        }
    }
}
