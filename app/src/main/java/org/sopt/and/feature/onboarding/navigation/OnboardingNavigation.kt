package org.sopt.and.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.feature.main.home.navigation.Home
import org.sopt.and.feature.onboarding.login.LogInRoute
import org.sopt.and.feature.onboarding.login.navigation.LogIn
import org.sopt.and.feature.onboarding.signup.SignUpRoute
import org.sopt.and.feature.onboarding.signup.navigation.SignUp

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController
) {
    composable<LogIn> {
        LogInRoute(
            navigateToSignUp = {
                navController.navigate(SignUp)
            },
            navigateToMain = {
                navController.navigate(Home)
            }
        )
    }
    composable<SignUp> {
        SignUpRoute(
            navigateToLogIn = { id, password ->
                navController.navigate(LogIn(id, password))
            }
        )
    }
}