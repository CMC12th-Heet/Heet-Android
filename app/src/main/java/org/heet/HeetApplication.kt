package org.heet

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.heet.util.HFMDebugTree
import timber.log.Timber

@HiltAndroidApp
class HeetApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(HFMDebugTree())
        }
    }
}
