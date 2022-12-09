package com.hectre.timesheet.presentation.job

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hectre.common.base.BaseFragment
import com.hectre.timesheet.BR
import com.hectre.timesheet.R
import com.hectre.timesheet.databinding.FragmentJobListBinding
import com.hectre.timesheet.presentation.job.adapter.JobAdapter
import com.hectre.utility.LogUtil
import com.hectre.utility.MainEvent
import com.hectre.utility.MainEventDispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@AndroidEntryPoint
class JobListFragment :
    BaseFragment<JobListViewModel, FragmentJobListBinding>() {

    private val viewModel by viewModels<JobListViewModel>()
    private val jobAdapter by lazy {
        JobAdapter(
            ::handleAddMaxTrees,
            ::onClickApplyToAll,
            viewModel::onClickRateType
        )
    }

    private fun onClickApplyToAll(jobId: Int?, pieceRate: String) {
//        findNavController().navigate(R.id.fragment_test_2)
        lifecycleScope.launch {
            doSomething(5)
        }
    }

    suspend fun doSomething(input: Int): Int {
        return suspendCoroutine { continuation ->
            if (input == 5) {
                continuation.resumeWithException(Exception("Input should not be 5"))
            } else {
                val result = libFun(input)
                continuation.resume(result)
            }
        }
    }

    fun libFun(input: Int): Int {
        var result = 0
        for (i in 0..input) {
            Log.d("CHECKING", "Cur = $i")
            result += i
        }
        return result
    }

    private fun handleAddMaxTrees(jobId: Int?) {
        viewLifecycleOwner.lifecycleScope.launch {
            MainEventDispatcher.dispatchEvent(MainEvent.OpenTestScreen)
        }
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
