package com.genix.architecturecomponents.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.genix.architecturecomponents.db.UserDao
import com.genix.architecturecomponents.util.InstantAppExecutors
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var userDao: UserDao

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(userDao, InstantAppExecutors())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getUpdatedName() {
        val name = MutableLiveData<String>()
        `when`(userDao.updateUserName("abc", "12345")).thenReturn(0)
        `when`(userDao.findName("12345")).thenReturn(name)
        mainViewModel.updateName("abc", "12345")
        mainViewModel.getName("12345").observeForever { assertEquals("abc", it) }
        name.value = "abc"
    }

    @Test
    fun getUpdatedPassword() {
        val pwd = MutableLiveData<String>()
        `when`(userDao.updatePassword("abc@123", "12345")).thenReturn(0)
        `when`(userDao.findPwd("12345")).thenReturn(pwd)
        mainViewModel.updatePassword("abc@123", "12345")
        mainViewModel.getPwd("12345").observeForever { assertEquals("abc@123", it) }
        pwd.value = "abc@123"
    }
}