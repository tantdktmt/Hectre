package com.hectre.utility

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.hectre.config.Constant
import java.util.regex.Pattern

class CharRestrictionTextWatcher : TextWatcher {

    private val pattern = Pattern.compile(Constant.REGEX_NUMBER_ONLY)
    private var originalText: String? = null
    private var editText: EditText? = null

    fun attachEditText(editText: EditText): CharRestrictionTextWatcher {
        this.editText = editText
        return this
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        originalText = s?.toString()
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable) {
        try {
            val text = s.toString()
            if (!pattern.matcher(text).matches()) {
                editText?.run {
                    removeTextChangedListener(this@CharRestrictionTextWatcher)
                    setText(originalText)
                    setSelection(originalText?.length ?: return)
                    addTextChangedListener(this@CharRestrictionTextWatcher)
                }
            }
        } catch (ex: Exception) {
            LogUtil.e("Error $ex")
        }
    }
}
