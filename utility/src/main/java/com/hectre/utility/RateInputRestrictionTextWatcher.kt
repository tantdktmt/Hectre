package com.hectre.utility

import com.hectre.config.Constant

class RateInputRestrictionTextWatcher : CharRestrictionTextWatcher(Constant.REGEX_NUMBER_OR_ZERO_OR_EMPTY)