package org.sopt.and.feature.main.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import org.sopt.and.R
import org.sopt.and.feature.main.home.navigation.Home
import org.sopt.and.feature.main.profile.navigation.Profile
import org.sopt.and.feature.main.save.navigation.Save
import org.sopt.and.navigation.MainTabRoute
import org.sopt.and.navigation.Route

enum class MainTab(
    @DrawableRes val icon: Int,
    val contentDescription: String,
    val route: MainTabRoute,
) {
    HOME(
        icon = R.drawable.ic_home,
        contentDescription = "home",
        route = Home
    ),
    SAVE(
        icon = R.drawable.ic_save,
        contentDescription = "save",
        route = Save
    ),
    PROFILE(
        icon = R.drawable.ic_profile,
        contentDescription = "profile",
        route = Profile
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}