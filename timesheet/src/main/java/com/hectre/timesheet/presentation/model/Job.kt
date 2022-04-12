package com.hectre.timesheet.presentation.model

import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.entity.RowEntity

data class Job(
    val id: Int?,
    val name: String?,
    val specificName: String?,
    val staffFirstName: String?,
    val staffLastName: String?,
    val orchard: String?,
    val block: String?,
    val pieceRate: Int?,
    val listRow: List<Row?>?
) {

    companion object {

        private const val DEFAULT_PIECE_RATE = 35

        fun create(jobEntity: JobEntity, listRowEntity: List<RowEntity>) = Job(
            jobEntity.id,
            jobEntity.name,
            jobEntity.specificName,
            jobEntity.staffFirstName,
            jobEntity.staffLastName,
            jobEntity.orchard,
            jobEntity.block,
            DEFAULT_PIECE_RATE,
            jobEntity.listRowId?.map { id ->
                listRowEntity.find {
                    it.id == id
                }?.let {
                    Row.create(it).also {
                        it.completedByStaff = 2
                    }
                }
            })
    }
}