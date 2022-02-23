package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.search.SearchHistory
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

interface GetSearchHistoriesUseCase : KoinComponent {
    suspend operator fun invoke(query: String): Flow<List<SearchHistory>>
}
