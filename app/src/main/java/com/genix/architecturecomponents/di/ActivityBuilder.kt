package com.genix.architecturecomponents.di

import com.genix.architecturecomponents.ui.login.LoginActivity
import com.genix.architecturecomponents.ui.login.LoginActivityModule
import com.genix.architecturecomponents.ui.main.MainActivity
import com.genix.architecturecomponents.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}