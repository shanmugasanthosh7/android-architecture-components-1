package com.sample.architecturecomponents.ui.login.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.architecturecomponents.R
import com.sample.architecturecomponents.ui.login.signin.SigninViewModel

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var loginViewModel: SigninViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.signup_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java)*/

        /*signupBtn.setOnClickListener {
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
        }*/
    }
}