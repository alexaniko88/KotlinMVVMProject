package com.aniko.mvvmproject.base

import android.app.Activity
import com.aniko.mvvmproject.home.MainActivity
import com.aniko.mvvmproject.home.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module(subcomponents = [MainActivityComponent::class])
abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun provideMainActivityInjector(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}