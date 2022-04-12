package com.hectre.timesheet.presentation.model

import com.hectre.timesheet.domain.entity.RowEntity

data class Row(
    val id: Int?,
    val label: String?,
    val totalTrees: Int?,
    val completedByOther: Int?,
    val otherStaffName: String?,
    var completedByStaff: Int? = null,
    var assigned: Boolean = false
) {

    companion object {

        fun create(rowEntity: RowEntity) = Row(
            rowEntity.id,
            rowEntity.label,
            rowEntity.totalTrees,
            rowEntity.completedByOther,
            rowEntity.otherStaffName
        )
    }
}