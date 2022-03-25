package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.video.common.CommonVideoResource
import kotlinx.coroutines.flow.Flow

interface SearchYoutubeUseCase {
    suspend operator fun invoke(query: String, pageToken: String): Flow<CommonVideoResource>
}
