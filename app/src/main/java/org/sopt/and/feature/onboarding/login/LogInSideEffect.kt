package org.sopt.and.feature.onboarding.login

sealed class LogInSideEffect {
    data class LogInError(val isIdError: Boolean) : LogInSideEffect()
    data object NavigateToSignUp : LogInSideEffect()
    data object NavigateToMain : LogInSideEffect()
}