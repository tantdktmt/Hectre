package com.hectre.timesheet.data

import android.content.Context
import com.hectre.common.data.dto.ResponseDto
import com.hectre.timesheet.data.dto.JobDto
import com.hectre.timesheet.domain.repository.TimesheetRepository
import com.hectre.utility.CommonUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject

class TimesheetRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val service: TimesheetService
) : TimesheetRepository {

    override suspend fun getListJob(): ResponseDto<List<JobDto>> {
        // Simulate fetching data from backend server
        delay(2000)
        val json = CommonUtil.loadJsonFromAsset(context, "jobs.json")
        return CommonUtil.parseJson(json)
    }
}
