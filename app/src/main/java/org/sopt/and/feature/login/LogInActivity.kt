package org.sopt.and.feature.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.designsystem.component.appbar.OnboardingTopBar
import org.sopt.and.designsystem.theme.ANDANDROIDTheme

@AndroidEntryPoint
class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        OnboardingTopBar(
                            title = "Wavve",
                            showBackBtn = false
                        )
                    }
                ) { innerPadding ->
                    LogInRoute(
                        modifier = Modifier.padding(paddingValues = innerPadding),
                        navigateToSignUp = ::navigateToSignUp,
                        navigateToMain = ::navigateToMain
                    )
                }
            }
        }
    }

    private fun navigateToSignUp() {
        // TODO
    }

    private fun navigateToMain(id: String) {
        // TODO
    }
}