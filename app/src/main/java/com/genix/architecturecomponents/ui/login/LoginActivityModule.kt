package com.genix.architecturecomponents.ui.login

import com.genix.architecturecomponents.ui.login.signin.SignInFragment
import com.genix.architecturecomponents.ui.login.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeSignUpFragment(): SignUpFragment

    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment
}