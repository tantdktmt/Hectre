package com.hectre.timesheet.presentation.model

import com.hectre.config.Constant
import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.entity.StaffEntity

object Mapper {

    fun buildListModel(listJobEntity: List<JobEntity>): List<BaseListModel> {
        val listModel = mutableListOf<BaseListModel>()
        var listStaffEntity: List<StaffEntity>?
        for (i in listJobEntity.indices) {
            // TESTING
            if (i > 0) break
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
                                this[j].listRow?.map {
                                    Row(
                                        it.id,
                                        it.label,
                                        it.totalTrees,
                                        it.treesCompletedByOther ?: 0,
                                        it.otherStaffName,
                                        it.treesCompletedByStaff
                                    )
                                })
                        )
                        // TESTING
//                        listModel.add(BaseListModel.ListDividerModel)
                    } else {
                        if (true) break
                        listModel.add(
                            LastStaffModel(
                                listJobEntity[i].jobId,
                                this[j].specificJobName,
                                this[j].staffFirstName,
                                this[j].staffLastName,
                                this[j].orchard,
                                this[j].block,
                                RateType.PIECE_RATE,
                                Constant.DEFAULT_PIECE_RATE,
                                this[j].listRow?.map {
                                    Row(
                                        it.id,
                                        it.label,
                                        it.totalTrees,
                                        it.treesCompletedByOther ?: 0,
                                        it.otherStaffName,
                                        it.treesCompletedByStaff
                                    )
                                })
                        )
                    }
                }
            }
        }
        // TESTING
//        listModel.add(BaseListModel.ListConfirmButtonModel)
        return listModel
    }
}