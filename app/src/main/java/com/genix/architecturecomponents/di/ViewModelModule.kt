package com.genix.architecturecomponents.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.genix.architecturecomponents.ui.login.LoginViewModel
import com.genix.architecturecomponents.viewmodelfactory.ArchViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginActivityModule: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ArchViewModelFactory): ViewModelProvider.Factory
}