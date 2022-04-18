package com.hectre.utility

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object ViewUtil {

    fun showKeyboard(view: View) {
        view.requestFocus()
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (view is EditText) imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}