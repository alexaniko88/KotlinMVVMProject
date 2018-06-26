package com.aniko.mvvmproject.base

import android.app.Application
import com.aniko.mvvmproject.di.ActivityInjector
import javax.inject.Inject


class MyApplication : Application() {

    @Inject
    lateinit var myActivityInjector : ActivityInjector

    private lateinit var component : ApplicationComponent

    override
    fun onCreate() {
        super.onCreate()


        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()


        component.inject(this)

    }

    fun getActivityInjector(): ActivityInjector {
        return this.myActivityInjector
    }
}