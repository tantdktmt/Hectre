package com.hectre.timesheet.presentation.model

import com.hectre.config.Constant
import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.entity.StaffEntity

object Mapper {

    fun buildListModel(listJobEntity: List<JobEntity>): List<BaseListModel> {
        val listModel = mutableListOf<BaseListModel>()
        var listStaffEntity: List<StaffEntity>?
        for (i in listJobEntity.indices) {
            listModel.add(HeaderModel(listJobEntity[i].jobId, listJobEntity[i].jobName))
            listStaffEntity = listJobEntity[i].listStaff
            listStaffEntity?.run {
                for (j in this.indices) {
                    if (j < this.size - 1) {
                        listModel.add(
                            StaffModel(
                                listJobEntity[i].jobId,
                                this[j].specificJobName,
                                this[j].staffFirstName,
                                this[j].staffLastName,
                                this[j].orchard,
                                this[j].block,
                                RateType.PIECE_RATE,
                                Constant.DEFAULT_PIECE_RATE,
                                false,
                                this[j].listRow?.map {
                                    Row(
                                        it.id,
                                        it.label,
                                        it.totalTrees ?: 0,
                                        it.totalTrees ?: 0,
                                        it.treesCompletedByOther ?: 0,
                                        it.otherStaffName,
                                        it.treesCompletedByStaff ?: 0
                                    )
                                })
                        )
                        listModel.add(BaseListModel.DividerModel)
                    } else {
                        listModel.add(
                            StaffModel(
                                listJobEntity[i].jobId,
                                this[j].specificJobName,
                                this[j].staffFirstName,
                                this[j].staffLastName,
                                this[j].orchard,
                                this[j].block,
                                RateType.PIECE_RATE,
                                Constant.DEFAULT_PIECE_RATE,
                                true,
                                this[j].listRow?.map {
                                    Row(
                                        it.id,
                                        it.label,
                                        it.totalTrees ?: 0,
                                        it.totalTrees ?: 0,
                                        it.treesCompletedByOther ?: 0,
                                        it.otherStaffName,
                                        it.treesCompletedByStaff ?: 0
                                    )
                                })
                        )
                    }
                }
            }
        }
        listModel.add(BaseListModel.ConfirmButtonModel)
        return listModel
    }
}