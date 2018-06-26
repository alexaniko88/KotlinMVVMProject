package com.aniko.mvvmproject.trending

import com.aniko.mvvmproject.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Scope

@ScreenScope
@Subcomponent
interface TrendingReposComponent : AndroidInjector<TrendingReposController>{
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TrendingReposController>()
}