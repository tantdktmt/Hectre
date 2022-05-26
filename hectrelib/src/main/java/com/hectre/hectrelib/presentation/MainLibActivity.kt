package com.hectre.hectrelib.presentation

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.hectre.common.base.BaseActivity
import com.hectre.hectrelib.R
import com.hectre.hectrelib.databinding.ActivityMainBinding
import com.hectre.utility.MainEvent
import com.hectre.utility.MainEventDispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
open class MainLibActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private val viewModel by viewModels<MainActivityViewModel>()

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getAssociatedViewModel() = viewModel

    override fun observeDataChanged() {
        MainEventDispatcher.listener.onEach {
            when (it) {
                is MainEvent.MainDeepLinkNavigate -> {
                    // TODO: navigate to the deeplink
                }
            }
        }.launchIn(lifecycleScope)
    }
}
