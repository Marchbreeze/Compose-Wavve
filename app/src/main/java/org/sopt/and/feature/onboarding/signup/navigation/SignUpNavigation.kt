package org.sopt.and.feature.onboarding.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.and.navigation.Route

fun NavController.navigateToSignUp(
    navOptions: NavOptions? = null,
) {
    navigate(
        SignUp,
        navOptions
    )
}

@Serializable
data object SignUp : Route