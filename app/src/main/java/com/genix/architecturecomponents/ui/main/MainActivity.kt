package com.genix.architecturecomponents.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.genix.architecturecomponents.R
import com.genix.architecturecomponents.ui.login.LoginActivity
import com.genix.architecturecomponents.vo.Constants
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

    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainActivityViewModel::class.java)
        updateNameBtn.setOnClickListener {
            mainViewModel.updateName(editName.text.toString(),
                    prefs.getString(Constants.USER_ID, null))
        }

        updatePwdBtn.setOnClickListener {
            mainViewModel.updatePassword(editPassword.text.toString(),
                    prefs.getString(Constants.USER_ID, null))
        }

        logout.setOnClickListener {
            prefsEdit.clear().commit()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.name.observe(this, Observer { name.text = it })
        mainViewModel.pwd.observe(this, Observer { password.text = it })
    }
}