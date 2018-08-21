package com.genix.architecturecomponents.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genix.architecturecomponents.AppExecutors
import com.genix.architecturecomponents.db.UserDao
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(private val userDao: UserDao,
                    private val appExecutors: AppExecutors) : ViewModel() {

    private val updatedName = MutableLiveData<String>()

    private val updatedPwd = MutableLiveData<String>()

    val name get() = updatedName

    val pwd get() = updatedPwd

    fun updateName(name: String, id: String) {
        appExecutors.diskIO().execute {
            val count = userDao.updateUserName(name, id)
            if (count > 0) appExecutors.mainThread().execute { updatedName.value = name }
        }
    }

    fun updatePassword(pwd: String, id: String) {
        appExecutors.diskIO().execute {
            val count = userDao.updatePassword(pwd, id)
            if (count > 0) appExecutors.mainThread().execute { updatedPwd.value = pwd }
        }
    }
}