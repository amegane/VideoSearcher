package com.amegane3231.moviesearch.android.application

import android.app.Application
import com.amegane3231.moviesearch.di.initKoin

class MovieSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}