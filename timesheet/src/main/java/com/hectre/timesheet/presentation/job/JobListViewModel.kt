package com.hectre.timesheet.presentation.job

import androidx.lifecycle.viewModelScope
import com.hectre.common.base.BaseViewModel
import com.hectre.config.Constant
import com.hectre.timesheet.presentation.model.BaseListModel
import com.hectre.timesheet.presentation.model.ModelUtil
import com.hectre.timesheet.presentation.model.StaffModel
import com.hectre.timesheet.presentation.usecase.GetListJobUseCase
import com.hectre.utility.LogUtil
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
        viewModelScope.launch {
            showLoading()
            val listStaffNeedUpdate = _listJobModel.value.filterIsInstance<StaffModel>().filter {
                it.jobId == jobId
            }.map {
                it.deepCopy()
            }
            val assignedCount = hashMapOf<Int, Int>()
            listStaffNeedUpdate.forEach { staff ->
                staff.listAvailableRow?.forEach { row ->
                    if (row.assigned) {
                        if (assignedCount[row.id] == null) assignedCount[row.id] = 1
                        else assignedCount[row.id] = assignedCount[row.id]!! + 1
                    }
                }
            }
            listStaffNeedUpdate.forEach { staff ->
                staff.listAvailableRow?.forEach { row ->
                    if (row.assigned) {
                        row.treesAssignedToStaff =
                            ((row.totalTrees - row.treesCompletedByOther) / assignedCount[row.id]!!).toString()
                    } else {
                        row.treesAssignedToStaff = Constant.DEFAULT_TREES_ASSIGNED_TO_STAFF
                    }
                }
            }
            val listTemp = _listJobModel.value.map { model ->
                if (model is StaffModel) {
                    listStaffNeedUpdate.find {
                        it.staffId == model.staffId
                    } ?: model
                } else model
            }
            _listJobModel.value = listTemp
            hideLoading()
        }
    }

    fun onClickApplyToAll(jobId: Int?, pieceRate: String) {
        LogUtil.d("onClickApplyToAll: jobId=$jobId, pieceRate=$pieceRate")
        viewModelScope.launch {
            showLoading()
            val listStaffNeedUpdate = _listJobModel.value.filterIsInstance<StaffModel>().filter {
                it.jobId == jobId
            }.map {
                if (it.pieceRate != pieceRate) it.copy(pieceRate = pieceRate) else it
            }
            val listTemp = _listJobModel.value.map { model ->
                if (model is StaffModel) {
                    listStaffNeedUpdate.find {
                        it.staffId == model.staffId
                    } ?: model
                } else model
            }
            _listJobModel.value = listTemp
            hideLoading()
        }
    }

    fun onClickRateType(staffId: Int?, rateType: Int) {
        viewModelScope.launch {
            showLoading()
            val listTemp = _listJobModel.value.map { model ->
                if (model is StaffModel && model.staffId == staffId) model.copy(rateType = rateType) else model
            }
            _listJobModel.value = listTemp
            hideLoading()
        }
    }
}
