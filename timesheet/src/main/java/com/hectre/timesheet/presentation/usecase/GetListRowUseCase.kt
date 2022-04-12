package com.hectre.timesheet.presentation.usecase

import com.hectre.timesheet.domain.entity.RowEntity

interface GetListRowUseCase {

    suspend operator fun invoke(): List<RowEntity>?
}
