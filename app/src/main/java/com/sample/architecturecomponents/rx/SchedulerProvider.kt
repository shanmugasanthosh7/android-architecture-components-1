package com.sample.architecturecomponents.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun executorIo(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun newThread(): Scheduler

    fun computation(): Scheduler
}