package com.sample.architecturecomponents.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: MutableLiveData<Boolean>) {
    if (visible.value!!) view.visibility = View.VISIBLE else view.visibility = View.GONE
}