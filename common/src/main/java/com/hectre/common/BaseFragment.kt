package com.hectre.common

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VM : ViewModel, T : ViewDataBinding> : Fragment(){

    private var _viewModel: VM? = null
    val viewModel get() = _viewModel!!
    private var _binding: T? = null
    val binding get() = _binding!!

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    // TODO can be removed & use ext function viewModels() instead
    protected abstract fun getInstanceViewModel(): VM

    protected abstract fun observeViewModels()

    protected open fun initViews() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val consumed = onInterceptBackPressed()
                    if (!consumed) {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            }
        )
    }

    @IdRes
    open fun getBindingViewModelId(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Resources.ID_NULL
        } else {
            0
        }
    }

    protected open fun onInterceptBackPressed() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = getInstanceViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        if (getBindingViewModelId() != Resources.ID_NULL) {
            binding.setVariable(getBindingViewModelId(), viewModel)
        }
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModels()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
