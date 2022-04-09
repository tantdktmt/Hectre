package com.hectre.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun showLoading() {
        loading.value = true
    }

    fun hideLoading() {
        loading.value = false
    }
}
