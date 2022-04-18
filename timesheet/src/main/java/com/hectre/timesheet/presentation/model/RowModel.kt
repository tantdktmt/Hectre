package com.hectre.timesheet.presentation.model

data class RowModel(
    val id: Int,
    val label: String?,
    val totalTrees: Int,
    var availableTrees: Int,
    val treesCompletedByOther: Int,
    val otherStaffName: String?,
    var treesAssignedToStaff: String,
    var assigned: Boolean = true
) {

    companion object {

        const val ROW_ID_OTHER = -1
    }
}

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