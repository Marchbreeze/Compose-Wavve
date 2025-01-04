package org.sopt.and.feature.onboarding.signup

import android.content.Intent
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
import org.sopt.and.feature.onboarding.login.LogInActivity.Companion.EXTRA_ID
import org.sopt.and.feature.onboarding.login.LogInActivity.Companion.EXTRA_PASSWORD

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
                            showBackBtn = true,
                            onBackClick = ::finish,
                        )
                    }
                ) { innerPadding ->
                    SignUpRoute(
                        navigateToLogIn = ::navigateToLogin,
                        modifier = Modifier.padding(paddingValues = innerPadding),
                    )
                }
            }
        }
    }

    private fun navigateToLogin(id: String, password: String) {
        setResult(RESULT_OK, Intent().apply {
            putExtra(EXTRA_ID, id)
            putExtra(EXTRA_PASSWORD, password)
        })
        finish()
    }
}