package org.sopt.and.feature.main.save.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.and.navigation.MainTabRoute

fun NavController.navigateToSave(
    navOptions: NavOptions? = null,
) {
    navigate(
        Save,
        navOptions
    )
}

@Serializable
data object Save : MainTabRoute