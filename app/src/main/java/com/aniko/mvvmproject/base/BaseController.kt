package com.aniko.mvvmproject.base

import android.content.Context
import com.aniko.mvvmproject.di.Injector
import com.bluelinelabs.conductor.Controller

abstract class BaseController : Controller(){

    var inject = false

    override
    fun onContextAvailable(context: Context) {
        if(!inject){
            Injector.inject(this)
            inject = true
        }
        super.onContextAvailable(context)
    }

}