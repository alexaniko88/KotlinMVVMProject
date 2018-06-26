package com.aniko.mvvmproject.home

import com.aniko.mvvmproject.di.ActivityScope
import com.aniko.mvvmproject.ui.NavigationModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = [
    MainScreenBindingModule::class,
    NavigationModule::class
])
interface MainActivityComponent : AndroidInjector<MainActivity>{
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}