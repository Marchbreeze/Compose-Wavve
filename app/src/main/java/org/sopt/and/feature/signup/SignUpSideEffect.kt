package org.sopt.and.feature.signup

sealed class SignUpSideEffect {
    data class SignUpError(val isIdError: Boolean) : SignUpSideEffect()
    data object NavigateToLogIn : SignUpSideEffect()
}