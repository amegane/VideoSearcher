package com.amegane3231.videosearcher.di

import com.amegane3231.videosearcher.api.YoutubeApi
import com.amegane3231.videosearcher.api.YoutubeApiImpl
import com.amegane3231.videosearcher.database.SearchHistoryDatabase
import com.amegane3231.videosearcher.database.SearchHistoryDatabaseImpl
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataActionCreator
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataActionCreatorImpl
import com.amegane3231.videosearcher.flux.search.action.SearchActionCreator
import com.amegane3231.videosearcher.flux.search.action.SearchActionCreatorImpl
import com.amegane3231.videosearcher.flux.search.action.SearchHistoryActionCreator
import com.amegane3231.videosearcher.flux.search.action.SearchHistoryActionCreatorImpl
import com.amegane3231.videosearcher.flux.search.store.SearchHistoryStore
import com.amegane3231.videosearcher.flux.search.store.SearchHistoryStoreImpl
import com.amegane3231.videosearcher.flux.search.store.SearchStore
import com.amegane3231.videosearcher.flux.search.store.SearchStoreImpl
import com.amegane3231.videosearcher.repository.VideoSearchRepository
import com.amegane3231.videosearcher.repository.VideoSearchRepositoryImpl
import com.amegane3231.videosearcher.usecase.GetSearchHistoriesUseCase
import com.amegane3231.videosearcher.usecase.GetSearchHistoriesUseCaseImpl
import com.amegane3231.videosearcher.usecase.GetYoutubeVideoDataUseCase
import com.amegane3231.videosearcher.usecase.GetYoutubeVideoDataUseCaseImpl
import com.amegane3231.videosearcher.usecase.InsertSearchHistoryUseCase
import com.amegane3231.videosearcher.usecase.InsertSearchHistoryUseCaseImpl
import com.amegane3231.videosearcher.usecase.SearchYoutubeUseCase
import com.amegane3231.videosearcher.usecase.SearchYoutubeUseCaseImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
}

val commonModule = module {
    single { Dispatcher }
    single { YoutubeApiImpl() as YoutubeApi }
    single { SearchHistoryDatabaseImpl() as SearchHistoryDatabase }
    single { VideoSearchRepositoryImpl() as VideoSearchRepository }
    factory { SearchYoutubeUseCaseImpl() as SearchYoutubeUseCase }
    factory { GetYoutubeVideoDataUseCaseImpl() as GetYoutubeVideoDataUseCase }
    factory { InsertSearchHistoryUseCaseImpl() as InsertSearchHistoryUseCase }
    factory { GetSearchHistoriesUseCaseImpl() as GetSearchHistoriesUseCase }
    factory { SearchActionCreatorImpl() as SearchActionCreator }
    factory { SearchHistoryActionCreatorImpl() as SearchHistoryActionCreator }
    factory { GetVideoDataActionCreatorImpl() as GetVideoDataActionCreator }
    factory { SearchStoreImpl() as SearchStore }
    factory { SearchHistoryStoreImpl() as SearchHistoryStore }
}

inline fun <reified T> getKoinInstance(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}
