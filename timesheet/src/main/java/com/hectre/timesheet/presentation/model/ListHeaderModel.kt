package com.hectre.timesheet.presentation.model

data class ListHeaderModel(
    val jobId: Int?,
    val jobName: String?
) : BaseListModel.BaseListDataModel(ViewType.HEADER)
