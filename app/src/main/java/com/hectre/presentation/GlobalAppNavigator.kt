package com.hectre.presentation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.hectre.R
import com.hectre.extension.safeDeepLinkNavigate

class GlobalAppNavigator(private val navController: NavController) {

    fun navigateAndRemoveAllStack(uri: Uri) {
        val navOptions = NavOptions.Builder().apply {
            setPopUpTo(R.id.nav_main_graph, false)
            setLaunchSingleTop(true)
        }.build()
        navController.safeDeepLinkNavigate(
            uri,
            navOptions
        )
    }
}
