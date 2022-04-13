package com.hectre.timesheet.presentation.model

sealed class BaseListModel(var viewType: Int) {

    abstract class BaseListDataModel(viewType: Int = ViewType.UNDEFINED) : BaseListModel(viewType)

    object ListDividerModel : BaseListModel(ViewType.DIVIDER)

    object ListConfirmButtonModel : BaseListModel(ViewType.CONFIRM_BUTTON)

    object ViewType {

        const val UNDEFINED = -1
        const val HEADER = 0
        const val NORMAL_ITEM = 1
        const val LAST_ITEM = 2
        const val DIVIDER = 3
        const val CONFIRM_BUTTON = 4
    }
}
