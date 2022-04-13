package com.hectre.timesheet.presentation.model

data class HeaderModel(
    val jobId: Int?,
    val jobName: String?
) : BaseListModel.BaseListDataModel(ViewType.HEADER)
