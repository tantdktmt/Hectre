package com.hectre.timesheet.domain.usecase

import com.hectre.timesheet.domain.Mapper
import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.repository.TimesheetRepository
import com.hectre.timesheet.presentation.usecase.GetListJobUseCase
import javax.inject.Inject

class GetListJobUseCaseImpl @Inject constructor(
    private val repository: TimesheetRepository
) : GetListJobUseCase {

    override suspend fun invoke(): List<JobEntity>? {
        val listJobDto = repository.getListJob().data
        val listJobEntity = listJobDto?.map {
            Mapper.map(it)
        }
        return listJobEntity
    }
}
