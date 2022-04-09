package com.hectre.presentation

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.hectre.R
import com.hectre.common.BaseActivity
import com.hectre.databinding.ActivityMainBinding
import com.hectre.utility.MainEvent
import com.hectre.utility.MainEventDispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private val mainViewModel by viewModels<MainActivityViewModel>()

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getInstanceViewModel() = mainViewModel

    override fun observeViewModels() {
        MainEventDispatcher.listener.onEach {
            when (it) {
                is MainEvent.MainDeepLinkNavigate -> {
                    // TODO: navigate to the deeplink
                }
            }
        }.launchIn(lifecycleScope)
    }
}
