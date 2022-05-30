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
//        val json = CommonUtil.loadJsonFromAsset(context, "jobs.json")
        val json =
            "{\"status\":{\"code\":\"success\",\"message\":\"Success\"},\"data\":[{\"job_id\":1,\"job_name\":\"Pruning\",\"staffs\":[{\"id\":1,\"specific_job_name\":\"Canker Removal\",\"staff_first_name\":\"Barco update\",\"staff_last_name\":\"\",\"orchard\":\"Benji (V1394U)\",\"block\":\"UB13\",\"available_rows\":[{\"id\":3,\"label\":\"Trees for row 3\",\"total_trees\":400,\"trees_completed_by_other\":200,\"other_staff_name\":\"Yi Wan\",\"trees_assigned_to_staff\":4},{\"id\":4,\"label\":\"Trees for row 4\",\"total_trees\":556,\"trees_completed_by_other\":250,\"other_staff_name\":\"Elizabeth Jargrave\",\"trees_assigned_to_staff\":2}]},{\"id\":2,\"specific_job_name\":\"Canker Removal\",\"staff_first_name\":\"Henry\",\"staff_last_name\":\"Pham\",\"orchard\":\"Benji (V1394U)\",\"block\":\"UB13\",\"available_rows\":[{\"id\":3,\"label\":\"Trees for row 3\",\"total_trees\":400,\"trees_completed_by_other\":200,\"other_staff_name\":\"Yi Wan\",\"trees_assigned_to_staff\":6},{\"id\":4,\"label\":\"Trees for row 4\",\"total_trees\":556,\"trees_completed_by_other\":250,\"other_staff_name\":\"Elizabeth Jargrave\",\"trees_assigned_to_staff\":2},{\"id\":5,\"label\":\"Trees for row 5\",\"total_trees\":270,\"trees_completed_by_other\":0,\"other_staff_name\":\"\",\"trees_assigned_to_staff\":20}]}]},{\"job_id\":2,\"job_name\":\"Thinning\",\"staffs\":[{\"id\":3,\"specific_job_name\":\"Canker Removal\",\"staff_first_name\":\"Darijan\",\"staff_last_name\":\"\",\"orchard\":\"Benji (V1394U)\",\"block\":\"UB14\",\"available_rows\":[{\"id\":3,\"label\":\"Trees for row 3\",\"total_trees\":400,\"trees_completed_by_other\":200,\"other_staff_name\":\"Yi Wan\",\"trees_assigned_to_staff\":7},{\"id\":4,\"label\":\"Trees for row 4\",\"total_trees\":556,\"trees_completed_by_other\":250,\"other_staff_name\":\"Elizabeth Jargrave\",\"trees_assigned_to_staff\":2},{\"id\":5,\"label\":\"Trees for row 5\",\"total_trees\":270,\"trees_completed_by_other\":0,\"other_staff_name\":\"\",\"trees_assigned_to_staff\":20}]}]}]}"
        return CommonUtil.parseJson(json)
    }
}
