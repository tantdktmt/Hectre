package com.hectre.timesheet.data.dto

import com.google.gson.annotations.SerializedName

data class RowDto(
    val id: Int?,
    val label: String?,
    @SerializedName("total_trees") val totalTrees: Int?,
    @SerializedName("trees_completed_by_other") val treesCompletedByOther: Int?,
    @SerializedName("other_staff_name") val otherStaffName: String?,
    @SerializedName("trees_completed_by_staff") val treesCompletedByStaff: Int?
)