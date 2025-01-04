package org.sopt.and.feature.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import org.sopt.and.designsystem.component.appbar.OnboardingTopBar
import org.sopt.and.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.feature.main.component.MainBottomBar
import org.sopt.and.feature.main.navigation.MainNavigator
import org.sopt.and.feature.main.navigation.MainTab
import org.sopt.and.feature.main.navigation.mainNavGraph
import org.sopt.and.feature.main.navigation.rememberMainNavigator

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
            ) { snackbarData ->
                Text(
                    text = snackbarData.visuals.message
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            OnboardingTopBar(
                title = "Wavve",
            )
        },
        bottomBar = {
            MainBottomBar(
                tabs = MainTab.entries.toList(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate
            )
        },
        content = { paddingValue ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {
                    mainNavGraph()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ANDANDROIDTheme {
        MainScreen()
    }
}