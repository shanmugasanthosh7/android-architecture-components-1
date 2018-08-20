package com.genix.architecturecomponents.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genix.architecturecomponents.AppExecutors
import com.genix.architecturecomponents.db.UserDao
import com.genix.architecturecomponents.vo.User
import javax.inject.Inject

class LoginViewModel @Inject constructor(val userDao: UserDao,
                                         val appExecutors: AppExecutors) : ViewModel() {

    private val _login = MutableLiveData<Boolean>()

    val login get() = _login

    fun login(userName: String, password: String) {
        val access = userDao.login(userName, password)
        val isAccessed = access.value
        _login.value = isAccessed != null
    }

    fun createUser(user: User) {
        appExecutors.diskIO().execute {
            val rowId = userDao.insert(user)
            appExecutors.mainThread().execute {
                if (rowId > 0) {
                    login(user.userName, user.password!!)
                }
            }
        }
    }

    fun getUserById(id: String): LiveData<User> = userDao.findById(id)

    fun getUser(userName: String): LiveData<User> = userDao.findByUserName(userName)

}
