package com.revolve44.postmakermassive

import android.app.Application
import timber.log.Timber

class MassiveApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // init timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.tag("ars")
        }

    }
}