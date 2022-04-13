package com.hectre.timesheet.presentation.model

data class StaffModel(
    val jobId: Int?,
    val specificJobName: String?,
    val staffFirstName: String?,
    val staffLastName: String?,
    val orchard: String?,
    val block: String?,
    var rateType: Int,
    var pieceRate: String,
    val listRow: List<Row?>?
) : BaseListModel.BaseListDataModel(ViewType.STAFF) {

    val badgeText
        get() = if (!staffFirstName.isNullOrBlank()) "${staffFirstName[0]}" else "" + if (!staffLastName.isNullOrBlank()) " ${staffLastName[0]}" else ""

    val fullName
        get() = staffFirstName ?: "" + if (!staffLastName.isNullOrBlank()) " $staffLastName" else ""

    val badgeType
        get() = if (!staffFirstName.isNullOrBlank()) (staffFirstName[0] - 'A') % 3 else BadgeType.UNKNOWN // Simulate defining badge type

    object BadgeType {

        const val UNKNOWN = -1
        const val RED = 0
        const val GREEN = 1
        const val BROWN = 3
    }
}
