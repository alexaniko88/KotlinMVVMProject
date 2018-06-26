package com.aniko.mvvmproject.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.aniko.mvvmproject.R
import com.aniko.mvvmproject.di.Injector
import com.aniko.mvvmproject.di.ScreenInjector
import com.aniko.mvvmproject.ui.ScreenNavigator
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import java.util.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private const val INSTANCE_ID_KEY : String = "instance_id"
    }

    @Inject
    lateinit var myScreenInjector: ScreenInjector
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    lateinit var instanceId : String
    private lateinit var router : Router

    override
    fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        instanceId = if(savedInstanceState != null){
            savedInstanceState.getString(INSTANCE_ID_KEY)
        } else {
            UUID.randomUUID().toString()
        }
        Injector.inject(this)
        setContentView(layoutRes())

        val screenContainer: ViewGroup =
                findViewById(R.id.screen_container) ?:
                throw NullPointerException("Activity must have a view with id: screen_container")
        router = Conductor.attachRouter(this, screenContainer, savedInstanceState)
        screenNavigator.initWithRouter(router, initialScreen())
        monitorBackStack()
        super.onCreate(savedInstanceState, persistentState)
    }

    @LayoutRes
    protected abstract fun layoutRes() : Int

    protected abstract fun initialScreen(): Controller

    override
    fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(INSTANCE_ID_KEY, instanceId)
    }

    override
    fun onBackPressed() {
        if(!screenNavigator.pop()){
            super.onBackPressed()
        }
    }

    override
    fun onDestroy() {
        super.onDestroy()
        screenNavigator.clear()
        if(isFinishing){
            Injector.clearComponent(this)
        }
    }

    fun getScreenInjector(): ScreenInjector {
        return myScreenInjector
    }

    private fun monitorBackStack(){
        router.addChangeListener(object : ControllerChangeHandler.ControllerChangeListener {
            override
            fun onChangeCompleted(to: Controller?, from: Controller?, isPush: Boolean, container: ViewGroup, handler: ControllerChangeHandler) {
            }

            override
            fun onChangeStarted(to: Controller?, from: Controller?, isPush: Boolean, container: ViewGroup, handler: ControllerChangeHandler) {
                if(!isPush && from != null){
                    Injector.clearComponent(from)
                }

            }
        })
    }
}