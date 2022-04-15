package com.hectre.timesheet.presentation.model

data class StaffModel(
    val jobId: Int?,
    val staffId: Int?,
    val specificJobName: String?,
    val staffFirstName: String?,
    val staffLastName: String?,
    val fullName: String,
    val badgeText: String,
    val badgeType: Int,
    val orchard: String?,
    val block: String?,
    var rateType: Int,
    var pieceRate: String,
    val isLast: Boolean,
    var listAvailableRow: List<Row>?
) : BaseListModel.BaseListDataModel(ViewType.STAFF) {

    val listAssignedRow
        get() = listAvailableRow?.filter {
            it.assigned
        } ?: emptyList()

    fun updateListAvailableRow(updatedRow: Row) {
        listAvailableRow = listAvailableRow?.map {
            if (it.id == updatedRow.id) it.copy(assigned = !it.assigned) else it
        }
    }

    fun deepCopy(): StaffModel {
        val newItem = copy()
        newItem.listAvailableRow = newItem.listAvailableRow?.map {
            it.copy()
        }
        return newItem
    }

    object BadgeType {

        const val UNKNOWN = -1
        const val RED = 0
        const val GREEN = 1
        const val BROWN = 3
    }
}
