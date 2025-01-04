package org.sopt.and.feature.onboarding.signup

sealed class SignUpSideEffect {
    data class SignUpError(val isIdError: Boolean) : SignUpSideEffect()
    data object NavigateToLogIn : SignUpSideEffect()
}