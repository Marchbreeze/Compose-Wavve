package org.sopt.and.feature

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import org.sopt.and.feature.main.home.navigation.navigateToHome
import org.sopt.and.feature.main.navigation.MainTab
import org.sopt.and.feature.main.profile.navigation.navigateToProfile
import org.sopt.and.feature.main.save.navigation.navigateToSave
import org.sopt.and.feature.onboarding.login.navigation.LogIn

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = LogIn("", "")

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.SAVE -> navController.navigateToSave(navOptions)
            MainTab.PROFILE -> navController.navigateToProfile(navOptions)
        }
    }
}