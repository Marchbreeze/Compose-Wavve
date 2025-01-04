package org.sopt.and.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.feature.home.navigation.Home
import org.sopt.and.feature.home.navigation.navigateToHome
import org.sopt.and.feature.profile.navigation.navigateToProfile
import org.sopt.and.feature.save.navigation.navigateToSave

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = Home

    val currentTab: MainTab?
        @Composable get() = MainTab.find { route ->
            currentDestination?.route == route.toString()
        }

    @Composable
    fun showBottomBar(): Boolean = MainTab.contains { route ->
        currentDestination?.route == route.toString()
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

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}