package com.delighted.sampleapp

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class DelightedSampleApp : Application() {
    @Inject
    lateinit var threadPolicy: StrictMode.ThreadPolicy

    @Inject
    lateinit var vmPolicy: StrictMode.VmPolicy

    @Inject
    lateinit var tree: Timber.Tree

    @Inject
    lateinit var config: AppConfig

    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(threadPolicy)
        StrictMode.setVmPolicy(vmPolicy)
        Timber.plant(tree)
    }
}
