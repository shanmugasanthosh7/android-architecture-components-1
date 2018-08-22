package com.genix.architecturecomponents.ui.login.signup

import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.genix.architecturecomponents.R
import com.genix.architecturecomponents.ui.login.LoginViewModel
import com.genix.architecturecomponents.ui.login.signin.SignInFragment
import com.genix.architecturecomponents.ui.main.MainActivity
import com.genix.architecturecomponents.vo.Constants
import com.genix.architecturecomponents.vo.User
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.signup_fragment.*
import javax.inject.Inject

class SignUpFragment : DaggerFragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var prefsEdit: SharedPreferences.Editor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.signup_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java)

        signupBtn.setOnClickListener {
            val user = User(userName = username.text.toString(),
                    password = password.text.toString(),
                    name = name.text.toString())
            loginViewModel.createUser(user)
        }

        loginViewModel.login.observe(this, Observer {
            if (it != null) {
                prefsEdit.putString(Constants.USER_ID, it).commit()
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }
        })

        loginToAccount.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, SignInFragment.newInstance())
                    ?.commitNow()
        }
    }
}