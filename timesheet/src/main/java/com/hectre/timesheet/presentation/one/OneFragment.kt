package com.hectre.timesheet.presentation.one

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hectre.common.base.BaseFragment
import com.hectre.extension.safeNavigate
import com.hectre.extension.setSafeOnClickListener
import com.hectre.timesheet.BR
import com.hectre.timesheet.R
import com.hectre.timesheet.databinding.FragmentOneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneFragment :
    BaseFragment<FragOneViewModel, FragmentOneBinding>() {

    private val viewModel by viewModels<FragOneViewModel>()

    override fun getLayoutId() = R.layout.fragment_one

    override fun getBindingViewModelId() = BR.view_model

    override fun getAssociatedViewModel() = viewModel

    override fun initView() {
        binding.tvText.setSafeOnClickListener {
            val action = OneFragmentDirections.goToJobListAction()
            findNavController().safeNavigate(action)
        }
    }

    override fun observeDataChanged() {
    }
}
