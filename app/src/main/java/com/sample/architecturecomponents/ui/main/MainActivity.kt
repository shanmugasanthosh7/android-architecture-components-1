package com.sample.architecturecomponents.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sample.architecturecomponents.R
import com.sample.architecturecomponents.ui.login.LoginActivity
import com.sample.architecturecomponents.vo.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefs: SharedPreferences

    @Inject
    lateinit var prefsEdit: SharedPreferences.Editor

    private lateinit var mainViewModel: MainViewModel

    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        userId = prefs.getString(Constants.USER_ID, null)

        updateNameBtn.setOnClickListener {
            mainViewModel.updateName(editName.text.toString(), userId)
        }

        updatePwdBtn.setOnClickListener {
            mainViewModel.updatePassword(editPassword.text.toString(), userId)
        }

        logout.setOnClickListener {
            prefsEdit.clear().commit()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.getName(userId).observe(this, Observer { name.text = it })
        mainViewModel.getPwd(userId).observe(this, Observer { password.text = it })
    }
}