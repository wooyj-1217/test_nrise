package com.example.wooyj

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WippyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}