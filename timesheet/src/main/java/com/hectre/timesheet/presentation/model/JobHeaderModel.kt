package com.hectre.timesheet.presentation.model

data class JobHeaderModel(
    val jobId: Int?,
    val jobName: String?
) : BaseListModel.BaseListDataModel(ViewType.JOB_HEADER)
