package com.sample.architecturecomponents.di

import androidx.lifecycle.ViewModelProvider
import com.sample.architecturecomponents.viewmodelfactory.ArchViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ArchViewModelFactory): ViewModelProvider.Factory
}