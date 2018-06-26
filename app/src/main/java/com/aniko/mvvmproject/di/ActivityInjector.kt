package com.aniko.mvvmproject.di

import android.app.Activity
import android.content.Context
import com.aniko.mvvmproject.base.BaseActivity
import com.aniko.mvvmproject.base.MyApplication
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Provider

class ActivityInjector @Inject constructor(private val activityInjectors: @JvmSuppressWildcards Map<Class<out Activity>,
        Provider<AndroidInjector.Factory<out Activity>>>) {

    private var cache : Map<String, AndroidInjector<@JvmSuppressWildcards Activity>> = mutableMapOf()

    fun inject(activity: Activity){

        if(activity !is BaseActivity){
            throw IllegalArgumentException("Activity must extend BaseActivity")
        }

        val instanceId = activity.instanceId
        if (cache.containsKey(instanceId)) {
            ((cache[instanceId]) as AndroidInjector<Activity>).inject(activity)
            return
        }

        //noinspection unchecked
        val injectorFactory = activityInjectors[activity.javaClass]!!.get() as AndroidInjector.Factory<Activity>
        val injector = injectorFactory.create(activity)
        (cache as MutableMap)[instanceId] = injector
        injector.inject(activity)

    }

    fun clear(activity: Activity) {
        if (activity !is BaseActivity) {
            throw IllegalArgumentException("Activity must extend BaseActivity")
        }
        (cache as MutableMap).remove(activity.instanceId)
    }

    companion object {
        fun get(context: Context) : ActivityInjector {
            return (context.applicationContext as MyApplication).getActivityInjector()
        }
    }


}