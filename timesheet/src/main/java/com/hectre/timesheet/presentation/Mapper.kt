package com.hectre.timesheet.presentation

import com.hectre.config.Constant
import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.entity.StaffEntity
import com.hectre.timesheet.presentation.model.BaseListModel
import com.hectre.timesheet.presentation.model.ListHeaderModel
import com.hectre.timesheet.presentation.model.ListLastItemModel
import com.hectre.timesheet.presentation.model.ListNormalItemModel
import com.hectre.timesheet.presentation.model.Row

object Mapper {

    fun buildListModel(listJobEntity: List<JobEntity>): List<BaseListModel> {
        val listModel = mutableListOf<BaseListModel>()
        var listStaffEntity: List<StaffEntity>?
        for (i in listJobEntity.indices) {
            listModel.add(ListHeaderModel(listJobEntity[i].jobId, listJobEntity[i].jobName))
            // TESTING
            if (true) break
            listStaffEntity = listJobEntity[i].listStaff
            listStaffEntity?.run {
                for (j in this.indices) {
                    if (j < this.size - 1) {
                        listModel.add(
                            ListNormalItemModel(
                                listJobEntity[i].jobId,
                                this[j].specificJobName,
                                this[j].staffFirstName,
                                this[j].staffLastName,
                                this[j].orchard,
                                this[j].block,
                                Constant.DEFAULT_PIECE_RATE,
                                this[j].listRow?.map {
                                    Row(
                                        it.id,
                                        it.label,
                                        it.totalTrees,
                                        it.treesCompletedByOther,
                                        it.otherStaffName,
                                        it.treesCompletedByStaff
                                    )
                                })
                        )
                        listModel.add(BaseListModel.ListDividerModel)
                    } else {
                        listModel.add(
                            ListLastItemModel(
                                listJobEntity[i].jobId,
                                this[j].specificJobName,
                                this[j].staffFirstName,
                                this[j].staffLastName,
                                this[j].orchard,
                                this[j].block,
                                Constant.DEFAULT_PIECE_RATE,
                                this[j].listRow?.map {
                                    Row(
                                        it.id,
                                        it.label,
                                        it.totalTrees,
                                        it.treesCompletedByOther,
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