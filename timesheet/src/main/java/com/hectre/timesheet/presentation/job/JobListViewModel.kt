package com.hectre.timesheet.presentation.job

import androidx.lifecycle.viewModelScope
import com.hectre.common.base.BaseViewModel
import com.hectre.timesheet.presentation.model.BaseListModel
import com.hectre.timesheet.presentation.model.ModelUtil
import com.hectre.timesheet.presentation.model.StaffModel
import com.hectre.timesheet.presentation.model.containsRowExt
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
            if (listStaffNeedUpdate.isEmpty()) return@launch
            var listAssignedRowShortest = listStaffNeedUpdate[0].listAssignedRow
            listStaffNeedUpdate.forEach {
                if (it.listAssignedRow.size < listAssignedRowShortest.size) listAssignedRowShortest =
                    it.listAssignedRow
            }
            var shouldUpdateData = false
            listAssignedRowShortest.forEach { row ->
                var isAssignedByAllStaffs = true
                for (i in listStaffNeedUpdate.indices) {
                    if (!listStaffNeedUpdate[i].listAssignedRow.containsRowExt(row)) {
                        isAssignedByAllStaffs = false
                        break
                    }
                }
                if (isAssignedByAllStaffs) {
                    shouldUpdateData = true
                    val newAvailableTrees =
                        (row.totalTrees - row.treesCompletedByOther) / listStaffNeedUpdate.size
                    listStaffNeedUpdate.forEach {
                        it.listAssignedRow.find {
                            it.id == row.id
                        }?.availableTrees = newAvailableTrees
                    }
                }
            }
            if (shouldUpdateData) {
                val listTemp = _listJobModel.value.map { model ->
                    if (model is StaffModel) {
                        listStaffNeedUpdate.find {
                            it.staffId == model.staffId
                        } ?: model
                    } else model
                }
                _listJobModel.value = listTemp
            }
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
