package com.hectre.utility

import android.text.Editable
import com.hectre.config.Constant

class TreeInputRestrictionTextWatcher : CharRestrictionTextWatcher(Constant.REGEX_NUMBER_OR_EMPTY) {

    private var maxTrees = Int.MAX_VALUE

    fun setMaxTrees(maxTrees: Int): TreeInputRestrictionTextWatcher {
        this.maxTrees = maxTrees
        return this
    }

    override fun afterTextChanged(s: Editable) {
        try {
            val strTreesInput = s.toString()
            val treesInput = strTreesInput.toIntOrNull() ?: 0
            if (treesInput > maxTrees || !pattern.matcher(strTreesInput).matches()) {
                editText?.run {
                    removeTextChangedListener(this@TreeInputRestrictionTextWatcher)
                    setText(originalText)
                    setSelection(originalText?.length ?: return)
                    addTextChangedListener(this@TreeInputRestrictionTextWatcher)
                }
            }
        } catch (ex: Exception) {
            LogUtil.e("Error $ex")
        }
    }
}