package org.sopt.and.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import org.sopt.and.designsystem.component.appbar.OnboardingTopBar
import org.sopt.and.designsystem.theme.ANDANDROIDTheme

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(paddingValue)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination,
                ) {
//                    homeNavGraph(
//                        navigateToFortuneDetail = { date ->
//                            navController.navigate(FortuneDetail(date))
//                        }
//                    )
//
//                    fortuneDetailNavGraph(
//                        navigateToFortuneAmulet = {
//                            navController.navigate(FortuneAmulet)
//                        },
//                        isBottomSheetVisible = {
//                            isBottomSheetVisible = it
//                        },
//                        snackBarHostState = snackBarHostState,
//                        showSnackBar = { case ->
//                            snackBarUiState = case
//                        },
//                    )
//
//                    fortuneAmuletNavGraph(
//                        navigateToHome = navigateToHome
//                    )
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