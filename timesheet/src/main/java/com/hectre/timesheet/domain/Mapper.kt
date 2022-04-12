package com.hectre.timesheet.domain

import com.hectre.timesheet.data.dto.JobDto
import com.hectre.timesheet.data.dto.RowDto
import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.entity.RowEntity

object Mapper {

    fun map(jobDto: JobDto) = JobEntity(
        jobDto.id,
        jobDto.name,
        jobDto.specificName,
        jobDto.staffFirstName,
        jobDto.staffLastName,
        jobDto.orchard,
        jobDto.block,
        jobDto.listRowId
    )

    fun map(rowDto: RowDto) = RowEntity(
        rowDto.id,
        rowDto.label,
        rowDto.totalTrees,
        rowDto.completedByOther,
        rowDto.otherStaffName
    )
}