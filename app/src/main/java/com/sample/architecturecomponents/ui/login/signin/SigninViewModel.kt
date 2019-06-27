package com.sample.architecturecomponents.ui.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.architecturecomponents.Login
import com.sample.architecturecomponents.api.ArchApiService
import com.sample.architecturecomponents.result.Event
import com.sample.architecturecomponents.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SigninViewModel
@Inject constructor(val appService: ArchApiService,
                    val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val isProgressShown = MutableLiveData<Boolean>()

    private val _onClick = MutableLiveData<Event<Navigate>>()

    private val _userLoginDetails = MutableLiveData<Login>()

    private val _userLoginDetailsError = MutableLiveData<Throwable>()

    val userLoginDetails: LiveData<Login> get() = _userLoginDetails

    val userLoginDetailsError: LiveData<Throwable> get() = _userLoginDetailsError

    val onClick: LiveData<Event<Navigate>> get() = _onClick

    init {
        isProgressShown.value = false
    }

    fun login() {
        disposable.add(appService.login(username.value!!, password.value!!)
                .subscribeOn(schedulerProvider.executorIo())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        { _userLoginDetails.value = it },
                        { _userLoginDetailsError.value = it },
                        { isProgressShown.value = false },
                        { isProgressShown.value = true }
                )
        )

    }

    fun onCreateAccountClick() {
        _onClick.value = Event(Navigate.ON_CREATE_ACCOUNT_CLICK)
    }
}

enum class Navigate {
    ON_CREATE_ACCOUNT_CLICK
}