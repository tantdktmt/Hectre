package com.hectre.timesheet.presentation.job

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hectre.common.base.BaseFragment
import com.hectre.timesheet.BR
import com.hectre.timesheet.R
import com.hectre.timesheet.databinding.FragmentJobListBinding
import com.hectre.timesheet.presentation.job.adapter.JobAdapter
import com.hectre.utility.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class JobListFragment :
    BaseFragment<JobListViewModel, FragmentJobListBinding>() {

    private val viewModel by viewModels<JobListViewModel>()
    private val jobAdapter by lazy {
        JobAdapter(
            viewModel::handleAddMaxTrees,
            viewModel::onClickApplyToAll,
            viewModel::onClickRateType
        )
    }

    override fun getLayoutId() = R.layout.fragment_job_list

    override fun getBindingViewModelId() = BR.view_model

    override fun getAssociatedViewModel() = viewModel

    override fun initView() {
        binding.rvJob.apply {
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false).apply {
                    initialPrefetchItemCount = 2
                }
            setLayoutManager(layoutManager)
            setHasFixedSize(true)
            adapter = jobAdapter
        }
    }

    override fun observeDataChanged() {
        with(viewModel) {
            listJobModel.onEach {
                LogUtil.d("list job: ${it.size}")
                if (it.isNotEmpty()) {
                    jobAdapter.submitList(it)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.run {
            if (listJobModel.value.isEmpty()) {
                loadData()
            }
        }
    }
}
