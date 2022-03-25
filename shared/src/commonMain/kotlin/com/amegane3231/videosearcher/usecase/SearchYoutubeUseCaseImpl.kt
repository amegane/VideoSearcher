package com.amegane3231.videosearcher.usecase

import com.amegane3231.videosearcher.data.video.common.CommonVideoDetail
import com.amegane3231.videosearcher.data.video.common.CommonVideoResource
import com.amegane3231.videosearcher.repository.VideoSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchYoutubeUseCaseImpl : SearchYoutubeUseCase, KoinComponent {
    private val repository: VideoSearchRepository by inject()

    override suspend fun invoke(query: String, pageToken: String): Flow<CommonVideoResource> {
        return flow {
            val data = repository.searchYoutube(query, pageToken)
            val videoDetailList = buildList {
                data.items.forEach {
                    add(
                        CommonVideoDetail(
                            videoId = it.id.videoId,
                            title = it.snippet.title,
                            imageUrl = it.snippet.thumbnails.medium.url
                        )
                    )
                }
            }
            emit(
                CommonVideoResource(
                    nextPageToken = data.nextPageToken,
                    prevPageToken = data.prevPageToken,
                    videoDetailList = videoDetailList
                )
            )
        }
    }
}
