package com.hectre.timesheet.domain.repository

import com.hectre.common.data.dto.ResponseDto
import com.hectre.timesheet.data.dto.JobDto
import com.hectre.timesheet.data.dto.RowDto

interface TimesheetRepository {

    suspend fun getListJob(): ResponseDto<List<JobDto>>
}
