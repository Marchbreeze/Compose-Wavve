package org.sopt.and.feature.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.designsystem.component.modifier.clearFocus
import org.sopt.and.designsystem.component.modifier.noRippleClickable
import org.sopt.and.designsystem.component.textfield.OnboardingTextField
import org.sopt.and.designsystem.theme.ANDANDROIDTheme

@Composable
fun LogInRoute(
    navigateToSignUp: () -> Unit,
    navigateToMain: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LogInViewModel = hiltViewModel(),
) {
    val logInState by viewModel.logInState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.logInSideEffect, lifecycleOwner) {
        viewModel.logInSideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is LogInSideEffect.NavigateToSignUp -> {
                        navigateToSignUp()
                    }

                    is LogInSideEffect.NavigateToMain -> {
                        navigateToMain(logInState.id)
                    }

                    is LogInSideEffect.LogInError -> {
                        if (sideEffect.isIdError) {
                            Toast.makeText(context, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }

    LogInScreen(
        logInState = logInState,
        onLogInBtnClick = viewModel::postToLogIn,
        onSignUpBtnClick = viewModel::navigateToSignUp,
        onNextBtnClick = viewModel::navigateToMain,
        onIdChange = viewModel::updateId,
        onPwChange = viewModel::updatePassword,
        modifier = modifier
    )
}

@Composable
fun LogInScreen(
    logInState: LogInState,
    onLogInBtnClick: () -> Unit,
    onSignUpBtnClick: () -> Unit,
    onNextBtnClick: () -> Unit,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .clearFocus(),
    ) {
        OnboardingTextField(
            modifier = Modifier
                .padding(top = 100.dp)
                .padding(horizontal = 20.dp),
            value = logInState.id,
            placeholder = "이메일 주소",
            onValueChange = onIdChange,
            isPassword = false,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            )
        )

        OnboardingTextField(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 20.dp),
            value = logInState.password,
            placeholder = "비밀번호",
            onValueChange = onPwChange,
            isPassword = true,
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .padding(horizontal = 20.dp),
            onClick = onLogInBtnClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "로그인",
                fontSize = 16.sp,
                lineHeight = 30.sp
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 20.dp),
            onClick = onSignUpBtnClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "회원가입",
                fontSize = 16.sp,
                lineHeight = 30.sp
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
                .align(Alignment.CenterHorizontally)
                .noRippleClickable { onNextBtnClick() },
            text = "다음에 하기",
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    ANDANDROIDTheme {
        LogInScreen(
            logInState = LogInState(),
            onLogInBtnClick = { },
            onSignUpBtnClick = { },
            onNextBtnClick = { },
            onIdChange = { },
            onPwChange = { },
        )
    }
}