package com.genix.architecturecomponents.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genix.architecturecomponents.util.AppExecutors
import com.genix.architecturecomponents.db.UserDao
import com.genix.architecturecomponents.vo.User
import javax.inject.Inject

class LoginViewModel
@Inject constructor(private val userDao: UserDao,
                    private val appExecutors: AppExecutors) : ViewModel() {

    private val _login = MutableLiveData<String>()

    val login get() = _login

    fun login(userName: String, password: String) {
        appExecutors.diskIO().execute {
            val userId = userDao.login(userName, password)
            appExecutors.mainThread().execute {
                _login.value = userId
            }
        }
    }

    fun createUser(user: User) {
        appExecutors.diskIO().execute {
            val isExists = userDao.userNameExists(user.userName)
            if (isExists == 0) {
                val rowId = userDao.insert(user)
                appExecutors.mainThread().execute {
                    if (rowId > 0) login(user.userName, user.password!!)
                }
            }
        }
    }
}
