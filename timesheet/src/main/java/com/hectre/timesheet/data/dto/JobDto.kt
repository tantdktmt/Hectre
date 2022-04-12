package com.hectre.timesheet.data.dto

import com.google.gson.annotations.SerializedName

//data class JobListDto(
//    @SerializedName("jobs")
//    val listJob: List<JobDto>?
//)

data class JobDto(
    val id: Int?,
    val name: String?,
    @SerializedName("specific_name") val specificName: String?,
    @SerializedName("staff_first_name") val staffFirstName: String?,
    @SerializedName("staff_last_name") val staffLastName: String?,
    val orchard: String?,
    val block: String?,
    @SerializedName("available_rows") val listRowId: List<Int>?
)
