package com.hectre.timesheet.presentation.job

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hectre.common.base.BaseFragment
import com.hectre.timesheet.BR
import com.hectre.timesheet.R
import com.hectre.timesheet.databinding.FragmentJobListBinding
import com.hectre.utility.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class JobListFragment :
    BaseFragment<JobListViewModel, FragmentJobListBinding>(){

    private val viewModel by viewModels<JobListViewModel>()

    override fun getLayoutId() = R.layout.fragment_job_list

    override fun getBindingViewModelId() = BR.view_model

    override fun getAssociatedViewModel() = viewModel

    override fun initView() {
    }

    override fun observeDataChanged() {
        with(viewModel) {
            listJob.onEach {
                LogUtil.d("list job: ${it.size}")
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }
}
