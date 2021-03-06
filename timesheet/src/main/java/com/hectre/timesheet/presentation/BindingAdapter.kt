package com.hectre.timesheet.presentation

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.hectre.extension.setTextColorExt
import com.hectre.timesheet.R
import com.hectre.timesheet.presentation.model.StaffModel
import com.hectre.common.R as CommonR

@BindingAdapter("android:backgroundBadge")
fun setBackgroundBadge(view: View, badgeType: Int) {
    when (badgeType) {
        StaffModel.BadgeType.RED -> view.setBackgroundResource(R.drawable.item_staff_badge_red_bg)
        StaffModel.BadgeType.GREEN -> view.setBackgroundResource(R.drawable.item_staff_badge_green_bg)
        StaffModel.BadgeType.BROWN -> view.setBackgroundResource(R.drawable.item_staff_badge_brown_bg)
        else -> Unit
    }
}

@BindingAdapter("android:backgroundAndPaddingStaffItem")
fun setBackgroundAndPaddingStaffItem(view: View, isLast: Boolean) {
    if (isLast) {
        view.setBackgroundResource(R.drawable.item_last_staff_item_bg)
    } else {
        view.setBackgroundResource(R.drawable.item_staff_item_bg)
    }
    val paddingStart = view.context.resources.getDimensionPixelSize(R.dimen.item_staff_padding_start)
    val paddingEnd = view.context.resources.getDimensionPixelSize(R.dimen.item_staff_padding_end)
    val paddingVertical = view.context.resources.getDimensionPixelSize(R.dimen.item_staff_padding_top)
    view.setPadding(paddingStart, paddingVertical, paddingEnd, paddingVertical)
}

@BindingAdapter("android:rateSelected")
fun setRateTypeTextViewStyle(textView: TextView, selected: Boolean) {
    if (selected) {
        textView.setBackgroundResource(R.drawable.item_staff_rate_type_selected_bg)
        textView.setTextColorExt(CommonR.color.all_text_white)
        textView.typeface = Typeface.DEFAULT_BOLD
    } else {
        textView.setBackgroundResource(R.drawable.item_staff_rate_type_bg)
        textView.setTextColorExt(CommonR.color.all_black)
        textView.typeface = Typeface.DEFAULT
    }
}

@BindingAdapter("android:rowIdTextStyle")
fun setRowIdTextStyle(textView: TextView, assigned: Boolean) {
    if (assigned) {
        textView.setBackgroundResource(R.drawable.item_row_id_assigned_bg)
        textView.setTextColorExt(CommonR.color.all_text_white)
        textView.typeface = Typeface.DEFAULT_BOLD
    } else {
        textView.setBackgroundResource(R.drawable.item_row_id_bg)
        textView.setTextColorExt(CommonR.color.all_black)
        textView.typeface = Typeface.DEFAULT
    }
}
