package com.aniko.mvvmproject.ui

import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule {
    @Binds
    abstract fun provideScreenNavigator(screenNavigatorImpl: ScreenNavigatorImpl): ScreenNavigator
}