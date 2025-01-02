package org.sopt.and.feature.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.designsystem.component.appbar.OnboardingTopBar
import org.sopt.and.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.feature.main.MainActivity
import org.sopt.and.feature.signup.SignUpActivity

@AndroidEntryPoint
class LogInActivity : ComponentActivity() {

    private val logInViewModel: LogInViewModel by viewModels()

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            logInViewModel.signedId = result.data?.getStringExtra(EXTRA_ID).orEmpty()
            logInViewModel.signedPassword = result.data?.getStringExtra(EXTRA_PASSWORD).orEmpty()
        }
    }

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
        signUpLauncher.launch(Intent(this, SignUpActivity::class.java))
    }

    private fun navigateToMain(id: String) {
        Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_ID, id)
            startActivity(this)
        }
        finish()
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_PASSWORD = "EXTRA_PASSWORD"

    }
}