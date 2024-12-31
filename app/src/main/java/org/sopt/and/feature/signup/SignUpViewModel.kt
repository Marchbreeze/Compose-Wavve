package org.sopt.and.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    private val _signUpSideEffect = MutableSharedFlow<SignUpSideEffect>()
    val signUpSideEffect = _signUpSideEffect.asSharedFlow()

    fun postToSignUp() {
        viewModelScope.launch {
            if (!isIdValid(_signUpState.value.id)) {
                _signUpSideEffect.emit(SignUpSideEffect.SignUpError(true))
            } else if (!isPasswordValid(_signUpState.value.password)) {
                _signUpSideEffect.emit(SignUpSideEffect.SignUpError(false))
            } else {
                _signUpSideEffect.emit(SignUpSideEffect.NavigateToLogin)
            }
        }
    }

    private fun isIdValid(id: String) = id.matches(EMAIL_REGEX.toRegex())

    private fun isPasswordValid(password: String): Boolean {
        val categoriesCount = listOf(
            password.any { it.isUpperCase() },
            password.any { it.isLowerCase() },
            password.any { it.isDigit() },
            password.any { !it.isLetterOrDigit() }
        ).count { it }
        return password.length in 8..20 && (categoriesCount >= 3)
    }

    fun updateId(id: String) = _signUpState.update { it.copy(id = id) }

    fun updatePassword(password: String) = _signUpState.update { it.copy(password = password) }

    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$"
    }
}