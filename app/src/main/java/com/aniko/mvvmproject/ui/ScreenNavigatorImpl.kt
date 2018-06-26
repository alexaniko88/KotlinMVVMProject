package com.aniko.mvvmproject.ui

import com.aniko.mvvmproject.di.ActivityScope
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import javax.inject.Inject

@ActivityScope
class ScreenNavigatorImpl @Inject constructor() : ScreenNavigator {

    private var router: Router? = null

    override
    fun initWithRouter(router: Router, controller: Controller) {
        this.router = router
        if(!this.router!!.hasRootController()){
            this.router!!.setRoot(RouterTransaction.with(controller))
        }
    }

    override
    fun pop(): Boolean {
        return this.router != null && this.router!!.handleBack()
    }

    override
    fun clear() {
        this.router = null
    }
}