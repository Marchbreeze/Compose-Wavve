package org.sopt.and.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.feature.main.home.navigation.Home
import org.sopt.and.feature.main.home.HomeRoute
import org.sopt.and.feature.main.profile.navigation.Profile
import org.sopt.and.feature.main.profile.ProfileRoute
import org.sopt.and.feature.main.save.navigation.Save
import org.sopt.and.feature.main.save.SaveRoute

fun NavGraphBuilder.mainNavGraph() {
    composable<Home> {
        HomeRoute()
    }
    composable<Save> {
        SaveRoute()
    }
    composable<Profile> {
        ProfileRoute()
    }
}