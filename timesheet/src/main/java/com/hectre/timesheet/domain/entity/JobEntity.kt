package com.hectre.timesheet.domain.entity

data class JobEntity(
    val id: Int?,
    val name: String?,
    val specificName: String?,
    val staffFirstName: String?,
    val staffLastName: String?,
    val orchard: String?,
    val block: String?,
    val listRowId: List<Int>?
)