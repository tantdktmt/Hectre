package com.hectre.timesheet.presentation.model

import com.hectre.config.Constant
import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.entity.StaffEntity

object ModelUtil {

    fun buildListModel(listJobEntity: List<JobEntity>): List<BaseListModel> {
        val listModel = mutableListOf<BaseListModel>()
        var listStaffEntity: List<StaffEntity>?
        for (i in listJobEntity.indices) {
            listModel.add(JobHeaderModel(listJobEntity[i].jobId, listJobEntity[i].jobName))
            listStaffEntity = listJobEntity[i].listStaff
            listStaffEntity?.run {
                for (j in this.indices) {
                    if (j < this.size - 1) {
                        listModel.add(
                            StaffModel(
                                listJobEntity[i].jobId,
                                this[j].id,
                                this[j].specificJobName,
                                this[j].staffFirstName,
                                this[j].staffLastName,
                                generateStaffFullName(
                                    this[j].staffFirstName,
                                    this[j].staffLastName
                                ),
                                generateStaffBadgeText(
                                    this[j].staffFirstName,
                                    this[j].staffLastName
                                ),
                                generateStaffBadgeType(this[j].staffFirstName),
                                this[j].orchard,
                                this[j].block,
                                RateType.PIECE_RATE,
                                Constant.DEFAULT_PIECE_RATE,
                                false,
                                this[j].listRow?.map {
                                    RowModel(
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
                                this[j].id,
                                this[j].specificJobName,
                                this[j].staffFirstName,
                                this[j].staffLastName,
                                generateStaffFullName(
                                    this[j].staffFirstName,
                                    this[j].staffLastName
                                ),
                                generateStaffBadgeText(
                                    this[j].staffFirstName,
                                    this[j].staffLastName
                                ),
                                generateStaffBadgeType(this[j].staffFirstName),
                                this[j].orchard,
                                this[j].block,
                                RateType.PIECE_RATE,
                                Constant.DEFAULT_PIECE_RATE,
                                true,
                                this[j].listRow?.map {
                                    RowModel(
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

    private fun generateStaffFullName(staffFirstName: String?, staffLastName: String?): String {
        return staffFirstName ?: "" + if (!staffLastName.isNullOrBlank()) " $staffLastName" else ""
    }

    private fun generateStaffBadgeText(staffFirstName: String?, staffLastName: String?): String {
        return if (!staffFirstName.isNullOrBlank()) "${staffFirstName[0]}" else "" + if (!staffLastName.isNullOrBlank()) " ${staffLastName[0]}" else ""
    }

    private fun generateStaffBadgeType(staffFirstName: String?): Int {
        return if (!staffFirstName.isNullOrBlank()) (staffFirstName[0] - 'A') % 3 else StaffModel.BadgeType.UNKNOWN // Simulate defining badge type
    }
}