package com.genix.architecturecomponents.ui.login.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.genix.architecturecomponents.R
import com.genix.architecturecomponents.ui.login.LoginViewModel
import com.genix.architecturecomponents.ui.login.signup.SignUpFragment
import com.genix.architecturecomponents.ui.main.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.signin_fragment.*
import javax.inject.Inject

class SignInFragment : DaggerFragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.signin_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java)

        login.setOnClickListener {
            loginViewModel.login(username.text.toString(), password.text.toString())
        }

        loginViewModel.login.observe(this, Observer {
            if (it) {
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }
        })

        createAccount.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, SignUpFragment.newInstance())
                    ?.commitNow()
        }
    }
}