package com.hectre.timesheet.presentation

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.hectre.extension.setSpannedText
import com.hectre.extension.setTextColorExt
import com.hectre.timesheet.R
import com.hectre.timesheet.presentation.model.StaffModel
import com.hectre.common.R as CommonR

@BindingAdapter("android:backgroundBadge")
fun setBackgroundBadge(view: View, badgeType: Int) {
    when (badgeType) {
        StaffModel.BadgeType.RED -> view.setBackgroundResource(R.drawable.timesheet_staff_badge_red_bg)
        StaffModel.BadgeType.GREEN -> view.setBackgroundResource(R.drawable.timesheet_staff_badge_green_bg)
        StaffModel.BadgeType.BROWN -> view.setBackgroundResource(R.drawable.timesheet_staff_badge_brown_bg)
        else -> Unit
    }
}

@BindingAdapter("android:backgroundStaffItem")
fun setBackgroundStaffItem(view: View, isLast: Boolean) {
    if (isLast) {
        view.setBackgroundResource(R.drawable.timesheet_last_staff_item_bg)
    } else {
        view.setBackgroundResource(R.drawable.timesheet_staff_item_bg)
    }
}

@BindingAdapter("android:rateSelected")
fun setRateTypeTextViewStyle(textView: TextView, selected: Boolean) {
    if (selected) {
        textView.setBackgroundResource(R.drawable.timesheet_staff_item_rate_type_button_selected)
        textView.setTextColorExt(CommonR.color.all_text_white)
        textView.typeface = Typeface.DEFAULT_BOLD
    } else {
        textView.setBackgroundResource(R.drawable.timesheet_staff_item_rate_type_button)
        textView.setTextColorExt(CommonR.color.all_black)
        textView.typeface = Typeface.DEFAULT
    }
}

@BindingAdapter("android:rowIdTextStyle")
fun setRowIdTextStyle(textView: TextView, assigned: Boolean) {
    if (assigned) {
        textView.setBackgroundResource(R.drawable.timesheet_row_id_assigned_bg)
        textView.setTextColorExt(CommonR.color.all_text_white)
        textView.typeface = Typeface.DEFAULT_BOLD
    } else {
        textView.setBackgroundResource(R.drawable.timesheet_row_id_bg)
        textView.setTextColorExt(CommonR.color.all_black)
        textView.typeface = Typeface.DEFAULT
    }
}

@BindingAdapter("android:treesCompletedByStaff", "android:availableTrees")
fun setTreeInfoText(textView: TextView, treesCompletedByStaff: Int, availableTrees: Int) {
    val subText1 = "$treesCompletedByStaff"
    val subText2 = " / $availableTrees"
    textView.setSpannedText(subText1, subText2, CommonR.color.all_gray_2)
}
