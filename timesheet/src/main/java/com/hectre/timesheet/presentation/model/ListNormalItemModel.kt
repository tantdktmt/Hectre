package com.hectre.timesheet.presentation.model

data class ListNormalItemModel(
    val jobId: Int?,
    val specificJobName: String?,
    val staffFirstName: String?,
    val staffLastName: String?,
    val orchard: String?,
    val block: String?,
    val pieceRate: Int?,
    val listRow: List<Row?>?
) : BaseListModel.BaseListDataModel(ViewType.NORMAL_ITEM)
