package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.youtube.YoutubeVideoDetailData
import kotlinx.coroutines.flow.Flow

interface GetYoutubeVideoDataUseCase {
    suspend operator fun invoke(videoId: String): Flow<YoutubeVideoDetailData>
}
