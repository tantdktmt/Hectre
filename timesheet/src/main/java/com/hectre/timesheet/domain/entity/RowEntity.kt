package com.hectre.timesheet.domain.entity

data class RowEntity(
    val id: Int?,
    val label: String?,
    val totalTrees: Int?,
    val treesCompletedByOther: Int?,
    val otherStaffName: String?,
    val treesCompletedByStaff: Int?
)