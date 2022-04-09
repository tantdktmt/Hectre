package com.hectre.common

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel, T : ViewDataBinding> : AppCompatActivity() {

    private var _viewModel: VM? = null
    val viewModel get() = _viewModel!!
    private var _binding: T? = null
    val binding get() = _binding!!

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    // TODO can be removed & use ext function viewModels() instead
    protected abstract fun getInstanceViewModel(): VM

    protected abstract fun observeViewModels()

    protected open fun initData() = Unit

    protected open fun initViews() = Unit

    @IdRes
    open fun getContainerId(): Int {
        return ID_NULL
    }

    @IdRes
    open fun getBindingViewModelId(): Int {
        return ID_NULL
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = getInstanceViewModel()
        _binding = DataBindingUtil.setContentView(this, getLayoutId())
        if (getBindingViewModelId() != ID_NULL) {
            binding.setVariable(getBindingViewModelId(), viewModel)
        }
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        initData()
        initViews()
        observeViewModels()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val ID_NULL = -1
    }
}
