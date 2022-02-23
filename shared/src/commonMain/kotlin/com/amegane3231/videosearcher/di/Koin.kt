package com.amegane3231.videosearcher.di

import com.amegane3231.videosearcher.api.YoutubeApi
import com.amegane3231.videosearcher.api.YoutubeApiImpl
import com.amegane3231.videosearcher.database.SearchHistoryDatabase
import com.amegane3231.videosearcher.database.SearchHistoryDatabaseImpl
import com.amegane3231.videosearcher.flux.core.Dispatcher
import com.amegane3231.videosearcher.flux.search.*
import com.amegane3231.videosearcher.repository.VideoSearchRepository
import com.amegane3231.videosearcher.repository.VideoSearchRepositoryImpl
import com.amegane3231.videosearcher.usecase.*
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
    factory { InsertSearchHistoryUseCaseImpl() as InsertSearchHistoryUseCase }
    factory { GetSearchHistoriesUseCaseImpl() as GetSearchHistoriesUseCase }
    factory { SearchActionCreatorImpl() as SearchActionCreator }
    factory { SearchHistoryActionCreatorImpl() as SearchHistoryActionCreator }
    factory { SearchStore() }
    factory { SearchHistoryStore() }
}

inline fun <reified T> getKoinInstance(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}
