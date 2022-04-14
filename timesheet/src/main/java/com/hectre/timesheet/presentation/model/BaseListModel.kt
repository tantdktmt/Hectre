package com.hectre.timesheet.presentation.model

sealed class BaseListModel(val viewType: Int) {

    abstract class BaseListDataModel(viewType: Int = ViewType.UNDEFINED) : BaseListModel(viewType)

    object DividerModel : BaseListModel(ViewType.DIVIDER)

    object ConfirmButtonModel : BaseListModel(ViewType.CONFIRM_BUTTON)

    object ViewType {

        const val UNDEFINED = -1
        const val HEADER = 0
        const val STAFF = 1
        const val DIVIDER = 2
        const val CONFIRM_BUTTON = 3
    }
}
