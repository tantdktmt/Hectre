package com.hectre.timesheet.domain.entity

data class RowEntity(
    val id: Int?,
    val label: String?,
    val totalTrees: Int?,
    val completedByOther: Int?,
    val otherStaffName: String?,
)