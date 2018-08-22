package com.genix.architecturecomponents.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.genix.architecturecomponents.util.AppExecutors
import com.genix.architecturecomponents.db.UserDao
import javax.inject.Inject

class MainViewModel
@Inject constructor(private val userDao: UserDao,
                    private val appExecutors: AppExecutors) : ViewModel() {

    fun updateName(name: String, id: String) {
        appExecutors.diskIO().execute {
            userDao.updateUserName(name, id)
        }
    }

    fun updatePassword(pwd: String, id: String) {
        appExecutors.diskIO().execute {
            userDao.updatePassword(pwd, id)
        }
    }

    fun getName(id: String): LiveData<String> = userDao.findName(id)

    fun getPwd(id: String): LiveData<String> = userDao.findPwd(id)
}