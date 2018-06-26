package com.aniko.mvvmproject.base

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule internal constructor(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context {
        return application
    }
}