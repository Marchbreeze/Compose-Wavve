package org.sopt.and.feature.signup

data class SignUpState(
    val id: String = "",
    val password: String = "",
    val isButtonEnabled: Boolean = false
)