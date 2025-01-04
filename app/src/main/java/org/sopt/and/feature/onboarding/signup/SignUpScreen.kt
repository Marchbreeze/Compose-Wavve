package org.sopt.and.feature.onboarding.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
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
import org.sopt.and.R
import org.sopt.and.designsystem.component.modifier.clearFocus
import org.sopt.and.designsystem.component.modifier.noRippleClickable
import org.sopt.and.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.feature.onboarding.component.OnboardingTextField
import org.sopt.and.feature.onboarding.component.WhiteGrayText

@Composable
fun SignUpRoute(
    navigateToLogIn: (id: String, password: String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val signUpState by viewModel.signUpState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.signUpSideEffect, lifecycleOwner) {
        viewModel.signUpSideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.NavigateToLogIn -> {
                        navigateToLogIn(signUpState.id, signUpState.password)
                    }

                    is SignUpSideEffect.SignUpError -> {
                        if (sideEffect.isIdError) {
                            Toast.makeText(context, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }

    SignUpScreen(
        signUpState = signUpState,
        onSignUpBtnClick = viewModel::postToSignUp,
        onIdChange = viewModel::updateId,
        onPwChange = viewModel::updatePassword,
    )
}

@Composable
fun SignUpScreen(
    signUpState: SignUpState,
    onSignUpBtnClick: () -> Unit,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clearFocus(),
    ) {
        WhiteGrayText(
            modifier = Modifier.padding(
                top = 30.dp,
                start = 20.dp,
            ),
            fontSize = 20,
            whiteText = "이메일과 비밀번호",
            grayText = "만으로"
        )
        WhiteGrayText(
            modifier = Modifier.padding(
                top = 4.dp,
                start = 20.dp,
            ),
            fontSize = 20,
            whiteText = "Wavve를 즐길 수 ",
            grayText = "있어요!"
        )

        OnboardingTextField(
            modifier = Modifier
                .padding(top = 30.dp)
                .padding(horizontal = 20.dp),
            value = signUpState.id,
            placeholder = "wavve@example.com",
            onValueChange = onIdChange,
            isPassword = false,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            )
        )
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_info),
                contentDescription = "",
                tint = Color.Gray
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "로그인 및 비밀번호 찾기에 사용되니, 정확한 이메일을 입력해주세요",
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )
        }

        OnboardingTextField(
            modifier = Modifier
                .padding(top = 30.dp)
                .padding(horizontal = 20.dp),
            value = signUpState.password,
            placeholder = "Wavve 비밀번호 설정",
            onValueChange = onPwChange,
            isPassword = true,
        )
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_info),
                contentDescription = "",
                tint = Color.Gray
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 중복하여 입력해주세요.",
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Text(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .padding(vertical = 14.dp)
                .noRippleClickable { onSignUpBtnClick() },
            text = "Wavve 회원가입",
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    ANDANDROIDTheme {
        SignUpScreen(
            signUpState = SignUpState(),
            onSignUpBtnClick = { },
            onIdChange = { },
            onPwChange = { },
        )
    }
}