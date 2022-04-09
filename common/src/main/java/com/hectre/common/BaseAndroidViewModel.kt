package com.hectre.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {

    protected val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun showLoading() {
        loading.value = true
    }

    fun hideLoading() {
        loading.value = false
    }
}
