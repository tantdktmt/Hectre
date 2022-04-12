package com.hectre.utility

import android.util.Log
import com.hectre.config.Constant

object LogUtil {

    fun d(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(Constant.DEBUG_LOG_TAG, message)
        }
    }
}
