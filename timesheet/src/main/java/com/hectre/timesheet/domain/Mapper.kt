package com.hectre.timesheet.domain

import com.hectre.timesheet.data.dto.JobDto
import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.entity.RowEntity
import com.hectre.timesheet.domain.entity.StaffEntity

object Mapper {

    fun map(jobDto: JobDto) = JobEntity(
        jobDto.jobId,
        jobDto.jobName,
        jobDto.listStaff?.map {
            StaffEntity(
                it.id,
                it.specificJobName,
                it.staffFirstName,
                it.staffLastName,
                it.orchard,
                it.block,
                it.listRow?.map {
                    RowEntity(
                        it.id,
                        it.label,
                        it.totalTrees,
                        it.treesCompletedByOther,
                        it.otherStaffName,
                        it.treesCompletedByStaff
                    )
                })
        }
    )
}