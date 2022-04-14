package com.hectre.timesheet.presentation.job

import androidx.lifecycle.viewModelScope
import com.hectre.common.base.BaseViewModel
import com.hectre.timesheet.presentation.model.ModelUtil
import com.hectre.timesheet.presentation.model.BaseListModel
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
}
