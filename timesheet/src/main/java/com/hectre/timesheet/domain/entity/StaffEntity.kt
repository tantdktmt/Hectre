package com.hectre.timesheet.domain.entity

data class StaffEntity(
    val id: Int?,
    val specificJobName: String?,
    val staffFirstName: String?,
    val staffLastName: String?,
    val orchard: String?,
    val block: String?,
    val listRow: List<RowEntity>?
)