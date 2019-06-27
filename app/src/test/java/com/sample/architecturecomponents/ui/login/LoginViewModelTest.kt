package com.sample.architecturecomponents.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.architecturecomponents.Login
import com.sample.architecturecomponents.api.ArchApiService
import com.sample.architecturecomponents.rx.TestSchedulerProvider
import com.sample.architecturecomponents.ui.login.signin.LoginViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before

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
    lateinit var apiService: ArchApiService

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var scheduler: TestScheduler

    @Mock
    lateinit var response: Login

    @Before
    fun setUp() {
        scheduler = TestScheduler()
        loginViewModel = LoginViewModel(apiService, TestSchedulerProvider(scheduler))
    }

    @After
    fun tearDown() {
    }

    @Test
    fun login() {
        doReturn(Observable.just(response))
                .`when`(apiService)
                .login("abc", "abc")
        loginViewModel.userLoginDetails.observeForever { result ->
            assertEquals(response, result)
        }
        loginViewModel.login()
        scheduler.triggerActions()
    }
}