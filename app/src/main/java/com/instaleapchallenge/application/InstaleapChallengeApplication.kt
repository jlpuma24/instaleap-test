package com.instaleapchallenge.application

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.instaleapchallenge.framework.di.networkModule
import com.instaleapchallenge.framework.di.repositoryModules
import com.instaleapchallenge.framework.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class InstaleapChallengeApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@InstaleapChallengeApplication)
            modules(
                viewModelModules,
                repositoryModules,
                networkModule
            )
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        var instance: InstaleapChallengeApplication? = null
            private set
    }
}