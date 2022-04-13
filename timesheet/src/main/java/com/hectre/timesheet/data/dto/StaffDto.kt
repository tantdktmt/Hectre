package com.hectre.timesheet.data.dto

import com.google.gson.annotations.SerializedName

data class StaffDto(
    val id: Int?,
    @SerializedName("specific_job_name") val specificJobName: String?,
    @SerializedName("staff_first_name") val staffFirstName: String?,
    @SerializedName("staff_last_name") val staffLastName: String?,
    val orchard: String?,
    val block: String?,
    @SerializedName("available_rows") val listRow: List<RowDto>?
)