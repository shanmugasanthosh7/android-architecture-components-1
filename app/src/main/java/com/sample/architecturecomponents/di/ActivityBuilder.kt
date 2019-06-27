package com.sample.architecturecomponents.di

import com.sample.architecturecomponents.ui.login.LoginActivity
import com.sample.architecturecomponents.ui.login.signin.SignInModule
import com.sample.architecturecomponents.ui.main.MainActivity
import com.sample.architecturecomponents.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SignInModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}