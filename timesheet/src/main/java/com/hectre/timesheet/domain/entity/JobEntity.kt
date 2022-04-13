package com.hectre.timesheet.domain.entity

data class JobEntity(
    val jobId: Int?,
    val jobName: String?,
    val listStaff: List<StaffEntity>?
)

