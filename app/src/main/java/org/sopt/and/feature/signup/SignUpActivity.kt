package org.sopt.and.feature.signup

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
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        OnboardingTopBar(
                            title = "회원가입",
                            onBackClick = { finish() },
                            showBackBtn = true
                        )
                    }
                ) { innerPadding ->
                    SignUpRoute(
                        modifier = Modifier.padding(paddingValues = innerPadding),
                        navigateToLogIn = ::navigateToLogin,
                    )
                }
            }
        }
    }

    private fun navigateToLogin(id: String, password: String) {
        // TODO
    }
}