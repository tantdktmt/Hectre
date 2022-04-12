package com.hectre.timesheet.domain.usecase

import com.hectre.timesheet.domain.Mapper
import com.hectre.timesheet.domain.entity.RowEntity
import com.hectre.timesheet.domain.repository.TimesheetRepository
import com.hectre.timesheet.presentation.usecase.GetListRowUseCase
import javax.inject.Inject

class GetListRowUseCaseImpl @Inject constructor(
    private val repository: TimesheetRepository
) : GetListRowUseCase {

    override suspend fun invoke(): List<RowEntity>? {
        val listRowDto = repository.getListRow().data
        val listRowEntity = listRowDto?.map {
            Mapper.map(it)
        }
        return listRowEntity
    }
}
