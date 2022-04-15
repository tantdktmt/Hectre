package com.hectre.timesheet.presentation.job

import androidx.lifecycle.viewModelScope
import com.hectre.common.base.BaseViewModel
import com.hectre.timesheet.presentation.model.BaseListModel
import com.hectre.timesheet.presentation.model.ModelUtil
import com.hectre.timesheet.presentation.model.StaffModel
import com.hectre.timesheet.presentation.model.containsRowExt
import com.hectre.timesheet.presentation.usecase.GetListJobUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(
    private val getListJobUseCase: GetListJobUseCase
) : BaseViewModel() {

    private val _listJobModel: MutableStateFlow<List<BaseListModel>> by lazy {
        MutableStateFlow(mutableListOf())
    }
    val listJobModel = _listJobModel.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            showLoading()
            getListJobUseCase()?.let {
                _listJobModel.value = ModelUtil.buildListModel(it)
            }
            hideLoading()
        }
    }

    fun handleAddMaxTrees(jobId: Int?) {
        val listStaff = _listJobModel.value.filterIsInstance<StaffModel>().filter {
            it.jobId == jobId
        }.map {
            it.deepCopy()
        }
        if (listStaff.isEmpty()) return
        var listAssignedRowShortest = listStaff[0].listAssignedRow
        listStaff.forEach {
            if (it.listAssignedRow.size < listAssignedRowShortest.size) listAssignedRowShortest =
                it.listAssignedRow
        }
        var shouldUpdateData = false
        listAssignedRowShortest.forEach { row ->
            var isAssignedByAllStaffs = true
            for (i in listStaff.indices) {
                if (!listStaff[i].listAssignedRow.containsRowExt(row)) {
                    isAssignedByAllStaffs = false
                    break
                }
            }
            if (isAssignedByAllStaffs) {
                shouldUpdateData = true
                val newAvailableTrees =
                    (row.totalTrees - row.treesCompletedByOther) / listStaff.size
                listStaff.forEach {
                    it.listAssignedRow.find {
                        it.id == row.id
                    }?.availableTrees = newAvailableTrees
                }
            }
        }
        if (shouldUpdateData) {
            val listTemp = _listJobModel.value.map { model ->
                if (model is StaffModel) {
                    listStaff.find {
                        it.staffId == model.staffId
                    } ?: model
                } else model
            }
            _listJobModel.value = listTemp
        }
    }
}
