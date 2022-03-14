package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.youtube.YoutubeVideoDetailData
import com.amegane3231.videosearcher.repository.VideoSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetYoutubeVideoDataUseCaseImpl : GetYoutubeVideoDataUseCase, KoinComponent {
    private val repository: VideoSearchRepository by inject()

    override suspend fun invoke(videoId: String): Flow<YoutubeVideoDetailData> {
        return flow {
            emit(repository.getYoutubeVideoData(videoId))
        }
    }
}
