package com.hectre

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HectreApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}