package com.aniko.mvvmproject.home

import com.aniko.mvvmproject.R
import com.aniko.mvvmproject.base.BaseActivity
import com.aniko.mvvmproject.trending.TrendingReposController
import com.bluelinelabs.conductor.Controller

class MainActivity : BaseActivity() {

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initialScreen(): Controller {
        return TrendingReposController()
    }
}
