package com.hectre.timesheet.presentation.model

data class RowModel(
    val id: Int?,
    val label: String?,
    val totalTrees: Int,
    var availableTrees: Int,
    val treesCompletedByOther: Int,
    val otherStaffName: String?,
    val treesCompletedByStaff: Int,
    var assigned: Boolean = true
)

fun List<RowModel>.containsRowExt(row: RowModel): Boolean {
    forEach {
        if (it.id == row.id) return true
    }
    return false
}

fun List<RowModel>.containsRowWithId(id: Int?): Boolean {
    forEach {
        if (it.id == id) return true
    }
    return false
}