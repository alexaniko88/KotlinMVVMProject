package com.aniko.mvvmproject.home

import com.aniko.mvvmproject.di.ControllerKey
import com.aniko.mvvmproject.trending.TrendingReposComponent
import com.aniko.mvvmproject.trending.TrendingReposController
import com.bluelinelabs.conductor.Controller
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module(subcomponents = [TrendingReposComponent::class])
abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController::class)
    internal abstract fun bindTrendingReposInjector(builder: TrendingReposComponent.Builder): AndroidInjector.Factory<out Controller>
}