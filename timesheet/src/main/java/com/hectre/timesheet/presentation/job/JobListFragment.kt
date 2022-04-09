package com.hectre.timesheet.presentation.job

import androidx.fragment.app.viewModels
import com.hectre.common.BaseFragment
import com.hectre.timesheet.BR
import com.hectre.timesheet.R
import com.hectre.timesheet.databinding.FragmentJobListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobListFragment :
    BaseFragment<JobListViewModel, FragmentJobListBinding>(){

    private val jobListViewModel by viewModels<JobListViewModel>()

    override fun getLayoutId() = R.layout.fragment_job_list

    override fun getBindingViewModelId() = BR.view_model

    override fun getInstanceViewModel() = jobListViewModel

    override fun initViews() {
    }

    override fun observeViewModels() {
        viewModel.run {
        }
    }
}
