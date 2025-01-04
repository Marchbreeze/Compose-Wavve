package org.sopt.and.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.feature.home.navigation.Home
import org.sopt.and.feature.home.HomeRoute
import org.sopt.and.feature.profile.navigation.Profile
import org.sopt.and.feature.profile.ProfileRoute
import org.sopt.and.feature.save.navigation.Save
import org.sopt.and.feature.save.SaveRoute

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