package com.hectre.timesheet.presentation.model

data class LastStaffModel(
    val jobId: Int?,
    val specificJobName: String?,
    val staffFirstName: String?,
    val staffLastName: String?,
    val orchard: String?,
    val block: String?,
    var rateType: Int,
    var pieceRate: String,
    val listRow: List<Row?>?
) : BaseListModel.BaseListDataModel(ViewType.LAST_STAFF)
