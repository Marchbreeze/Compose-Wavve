package org.sopt.and.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.and.navigation.MainTabRoute

fun NavController.navigateToHome(
    navOptions: NavOptions? = null,
) {
    navigate(
        Home,
        navOptions
    )
}

@Serializable
data object Home : MainTabRoute