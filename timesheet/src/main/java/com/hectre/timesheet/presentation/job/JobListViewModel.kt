package com.hectre.timesheet.presentation.job

import androidx.lifecycle.viewModelScope
import com.hectre.common.base.BaseViewModel
import com.hectre.timesheet.domain.entity.JobEntity
import com.hectre.timesheet.domain.entity.RowEntity
import com.hectre.timesheet.presentation.model.Job
import com.hectre.timesheet.presentation.usecase.GetListJobUseCase
import com.hectre.timesheet.presentation.usecase.GetListRowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(
    private val getListJobUseCase: GetListJobUseCase,
    private val getListRowUseCase: GetListRowUseCase
) : BaseViewModel() {

    private val _listJob: MutableStateFlow<List<Job>> by lazy {
        MutableStateFlow(mutableListOf())
    }
    val listJob = _listJob.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            showLoading()
            var listJobEntity: List<JobEntity>? = null
            var listRowEntity: List<RowEntity>? = null
            awaitAll(
                async {
                    listJobEntity = getListJobUseCase()
                },
                async {
                    listRowEntity = getListRowUseCase()
                }
            )
            listJobEntity?.map {
                Job.create(it, listRowEntity ?: emptyList())
            }?.let {
                _listJob.value = it
            }
            hideLoading()
        }
    }
}
