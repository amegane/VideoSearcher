package com.amegane3231.videosearcher.android.application

import android.app.Application
import com.amegane3231.videosearcher.di.initKoin

class VideoSearcherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}