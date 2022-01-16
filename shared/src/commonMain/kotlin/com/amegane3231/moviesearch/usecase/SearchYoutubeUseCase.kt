package com.amegane3231.moviesearch.usecase

import com.amegane3231.moviesearch.data.youtube.YoutubeSearchedData
import com.amegane3231.moviesearch.repository.VideoSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchYoutubeUseCase : KoinComponent {
    private val repository: VideoSearchRepository by inject()

    suspend fun searchYoutube(query: String) : Flow<YoutubeSearchedData> {
        return flow {
            emit(repository.searchYoutube(query))
        }
    }
}