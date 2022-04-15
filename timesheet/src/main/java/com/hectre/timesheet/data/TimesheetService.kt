package com.hectre.timesheet.data

import com.hectre.common.data.dto.ResponseDto
import com.hectre.timesheet.data.dto.JobDto
import retrofit2.Response
import retrofit2.http.GET

interface TimesheetService {

    @GET("timesheet/list-job")
    suspend fun getListJob(): Response<ResponseDto<List<JobDto>>>
}
