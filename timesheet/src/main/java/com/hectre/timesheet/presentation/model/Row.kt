package com.hectre.timesheet.presentation.model

data class Row(
    val id: Int?,
    val label: String?,
    val totalTrees: Int?,
    val treesCompletedByOther: Int,
    val otherStaffName: String?,
    val treesCompletedByStaff: Int?,
    var assigned: Boolean = true
)