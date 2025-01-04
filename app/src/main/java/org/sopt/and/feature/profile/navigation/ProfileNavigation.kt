package org.sopt.and.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.and.navigation.MainTabRoute

fun NavController.navigateToProfile(
    navOptions: NavOptions? = null,
) {
    navigate(
        Profile,
        navOptions
    )
}

@Serializable
data object Profile : MainTabRoute