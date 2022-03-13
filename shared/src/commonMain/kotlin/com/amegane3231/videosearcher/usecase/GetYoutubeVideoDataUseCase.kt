package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.youtube.YoutubeSearchedData
import kotlinx.coroutines.flow.Flow

interface GetYoutubeVideoDataUseCase {
    suspend operator fun invoke(videoId: String): Flow<YoutubeSearchedData>
}
