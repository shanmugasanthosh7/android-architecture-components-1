package com.sample.architecturecomponents.ui.login

import android.os.Bundle
import com.sample.architecturecomponents.R
import com.sample.architecturecomponents.ui.login.signin.SignInFragment
import dagger.android.support.DaggerAppCompatActivity

class LoginActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SignInFragment.newInstance())
                    .commitNow()
        }
    }
}