package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.video.common.CommonVideoResource
import kotlinx.coroutines.flow.Flow

interface GetYoutubeVideoDataUseCase {
    suspend operator fun invoke(videoId: String): Flow<CommonVideoResource>
}
