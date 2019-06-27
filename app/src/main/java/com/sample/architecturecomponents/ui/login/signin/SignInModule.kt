package com.sample.architecturecomponents.ui.login.signin

import androidx.lifecycle.ViewModel
import com.sample.architecturecomponents.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SignInModule {

    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment

    @Binds
    @IntoMap
    @ViewModelKey(SigninViewModel::class)
    abstract fun bindLoginViewModel(signinViewModel: SigninViewModel): ViewModel

}