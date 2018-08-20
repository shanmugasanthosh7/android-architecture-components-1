package com.genix.architecturecomponents.di

import com.genix.architecturecomponents.ui.login.LoginActivity
import com.genix.architecturecomponents.ui.login.LoginActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun bindMainActivity(): LoginActivity
}