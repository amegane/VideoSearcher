package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData
import kotlinx.coroutines.flow.Flow

interface SearchYoutubeUseCase {
    suspend operator fun invoke(query: String): Flow<YoutubeSearchedData>
}
