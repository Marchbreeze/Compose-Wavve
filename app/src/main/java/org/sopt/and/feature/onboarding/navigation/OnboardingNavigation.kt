package org.sopt.and.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import org.sopt.and.feature.main.home.navigation.navigateToHome
import org.sopt.and.feature.onboarding.login.LogInRoute
import org.sopt.and.feature.onboarding.login.navigation.LogIn
import org.sopt.and.feature.onboarding.login.navigation.navigateToLogIn
import org.sopt.and.feature.onboarding.signup.SignUpRoute
import org.sopt.and.feature.onboarding.signup.navigation.SignUp
import org.sopt.and.feature.onboarding.signup.navigation.navigateToSignUp

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController
) {
    val singleTopNavOptions = navOptions {
        launchSingleTop = true
    }

    composable<LogIn> { backStackEntry ->
        val item = backStackEntry.toRoute<LogIn>()
        LogInRoute(
            signedId = item.id,
            signedPassword = item.password,
            navigateToSignUp = {
                navController.navigateToSignUp(singleTopNavOptions)
            },

            navigateToMain = {
                navController.navigateToHome(
                    navOptions {
                        popUpTo<LogIn> { inclusive = true }
                        launchSingleTop = true
                    }
                )
            }
        )
    }

    composable<SignUp> {
        SignUpRoute(
            navigateToLogIn = { id, password ->
                navController.navigateToLogIn(id, password, singleTopNavOptions)
            }
        )
    }
}