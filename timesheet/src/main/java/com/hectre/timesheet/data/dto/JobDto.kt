package com.hectre.timesheet.data.dto

import com.google.gson.annotations.SerializedName

data class JobDto(
    @SerializedName("job_id") val jobId: Int?,
    @SerializedName("job_name") val jobName: String?,
    @SerializedName("staffs") val listStaff: List<StaffDto>?
)

