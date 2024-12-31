package org.sopt.and.feature.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.designsystem.component.text.WhiteGrayText
import org.sopt.and.designsystem.theme.ANDANDROIDTheme

@Composable
fun SignUpRoute(
    navigateToLogIn: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val signUpState by viewModel.signUpState.collectAsStateWithLifecycle()
}

@Composable
fun SignUpScreen(
    onSignUpBtnClick: () -> Unit,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    signUpState: SignUpState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
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
            whiteText = "Wavve를 즐길 수",
            grayText = "있어요!"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    ANDANDROIDTheme {
        SignUpScreen(
            onSignUpBtnClick = { },
            modifier = Modifier,
            onIdChange = { },
            onPwChange = { },
            signUpState = SignUpState()
        )
    }
}