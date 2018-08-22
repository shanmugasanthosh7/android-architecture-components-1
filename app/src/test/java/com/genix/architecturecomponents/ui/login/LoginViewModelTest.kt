package com.genix.architecturecomponents.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.genix.architecturecomponents.db.UserDao
import com.genix.architecturecomponents.util.InstantAppExecutors
import com.genix.architecturecomponents.vo.User
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var userDao: UserDao

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(userDao, InstantAppExecutors())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun login() {
        `when`(userDao.login("abc", "test")).thenReturn(null)
        loginViewModel.login("abc", "test")
        loginViewModel.login.observeForever { assert(it == null) }
    }

    @Test
    fun createUser() {
        val user = User(name = "tap", userName = "abc", password = "test")
        `when`(userDao.userNameExists(user.userName)).thenReturn(0)
        `when`(userDao.insert(user)).thenReturn(1)
        `when`(userDao.login(user.userName, user.password!!)).thenReturn("")
        loginViewModel.createUser(user)
        loginViewModel.login.observeForever { assert(it == "") }
    }
}