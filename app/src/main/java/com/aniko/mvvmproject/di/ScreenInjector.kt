package com.aniko.mvvmproject.di

import android.app.Activity
import com.aniko.mvvmproject.base.BaseActivity
import com.aniko.mvvmproject.base.BaseController
import com.bluelinelabs.conductor.Controller
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Provider


@ActivityScope
class ScreenInjector @Inject constructor(private val screenInjectors: @JvmSuppressWildcards Map<Class<out Controller>, Provider<AndroidInjector.Factory<out Controller>>>) {

    private var cache : Map<String, AndroidInjector<Controller>> = mutableMapOf()

    fun inject(controller: Controller) {
        if (controller !is BaseController) {
            throw IllegalArgumentException("Controller must extend BaseController")
        }

        val instanceId = controller.getInstanceId()
        if (cache.containsKey(instanceId)) {
            ((cache[instanceId]) as AndroidInjector<Controller>).inject(controller)
            return
        }

        val injectorFactory = screenInjectors[controller.javaClass]!!.get() as AndroidInjector.Factory<Controller>
        val injector = injectorFactory.create(controller)
        (cache as MutableMap)[instanceId] = injector
        injector.inject(controller)
    }

    fun clear(controller: Controller) {
        (cache as MutableMap).remove(controller.instanceId)
    }

    companion object {
        fun get(activity: Activity) : ScreenInjector {
            if (activity !is BaseActivity) {
                throw IllegalArgumentException("Controller must be hosted by BaseActivity")
            }
            return activity.getScreenInjector()
        }
    }
}