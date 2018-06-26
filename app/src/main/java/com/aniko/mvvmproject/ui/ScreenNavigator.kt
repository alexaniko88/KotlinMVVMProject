package com.aniko.mvvmproject.ui

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router

interface ScreenNavigator {

    fun initWithRouter(router: Router, controller: Controller)
    fun pop(): Boolean
    fun clear()
}