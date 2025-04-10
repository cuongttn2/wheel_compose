package com.qsd.wheelcompose.ui

import android.app.Application
import com.qsd.wheelcompose.BuildConfig
import com.qsd.wheelcompose.model.data.local.prefs.AppPrefs
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPrefs.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}