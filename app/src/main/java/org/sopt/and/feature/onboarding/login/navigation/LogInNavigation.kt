package org.sopt.and.feature.onboarding.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.and.navigation.Route

fun NavController.navigateToLogIn(
    navOptions: NavOptions? = null,
) {
    navigate(
        LogIn,
        navOptions
    )
}

@Serializable
data class LogIn(
    val id: String,
    val password: String,
) : Route