package com.hectre.timesheet.presentation.model

data class StaffModel(
    val jobId: Int?,
    val specificJobName: String?,
    val staffFirstName: String?,
    val staffLastName: String?,
    val fullName: String,
    val badgeText: String,
    val badgeType: Int,
    val orchard: String?,
    val block: String?,
    var rateType: Int,
    var pieceRate: String,
    val isLast: Boolean,
    val listAvailableRow: List<Row?>?
) : BaseListModel.BaseListDataModel(ViewType.STAFF) {

    val listAssignedRow
        get() = listAvailableRow?.filter {
            it?.assigned == true
        }

    object BadgeType {

        const val UNKNOWN = -1
        const val RED = 0
        const val GREEN = 1
        const val BROWN = 3
    }
}
