package org.sopt.and.feature.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.feature.home.Home
import org.sopt.and.feature.home.HomeRoute
import org.sopt.and.feature.profile.Profile
import org.sopt.and.feature.profile.ProfileRoute
import org.sopt.and.feature.save.Save
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