package com.hectre.timesheet.presentation.usecase

import com.hectre.timesheet.domain.entity.JobEntity

interface GetListJobUseCase {

    suspend operator fun invoke(): List<JobEntity>?
}
