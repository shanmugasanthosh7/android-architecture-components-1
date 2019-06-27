package com.sample.architecturecomponents.ui.login.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sample.architecturecomponents.R
import com.sample.architecturecomponents.base.BaseFragment
import com.sample.architecturecomponents.databinding.SigninFragmentBinding
import com.sample.architecturecomponents.result.EventObserver
import com.sample.architecturecomponents.ui.login.signup.SignUpFragment
import com.sample.architecturecomponents.ui.main.MainActivity
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var loginViewModel: SigninViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        val binding: SigninFragmentBinding =
                DataBindingUtil.inflate(inflater,
                        R.layout.signin_fragment,
                        container,
                        false
                )
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SigninViewModel::class.java)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        loginViewModel.userLoginDetails.observe(this, Observer {
            // Once the login is success the token will return here
            it.let {
                it.token.let {
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity?.finish()
                }
            }
        })
        loginViewModel.userLoginDetailsError.observe(viewLifecycleOwner, Observer {
            // We can show the error here
        })
        loginViewModel.onClick.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                Navigate.ON_CREATE_ACCOUNT_CLICK -> {
                    activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.container, SignUpFragment.newInstance())
                            ?.commitNow()
                }
            }
        })
        return binding.root
    }
}