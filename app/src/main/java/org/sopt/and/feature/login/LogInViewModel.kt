package org.sopt.and.feature.login

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
class LogInViewModel @Inject constructor() : ViewModel() {
    private val _logInState = MutableStateFlow(LogInState())
    val logInState = _logInState.asStateFlow()

    private val _logInSideEffect = MutableSharedFlow<LogInSideEffect>()
    val logInSideEffect = _logInSideEffect.asSharedFlow()

    var signedId = ""
    var signedPassword = ""

    fun updateId(id: String) = _logInState.update { it.copy(id = id) }

    fun updatePassword(password: String) = _logInState.update { it.copy(password = password) }

    fun postToLogIn() {
        viewModelScope.launch {
            if (_logInState.value.id != signedId) {
                _logInSideEffect.emit(LogInSideEffect.LogInError(true))
            } else if (_logInState.value.password != signedPassword) {
                _logInSideEffect.emit(LogInSideEffect.LogInError(false))
            } else {
                _logInSideEffect.emit(LogInSideEffect.NavigateToMain)
            }
        }
    }

    fun navigateToSignUp() {
        viewModelScope.launch {
            _logInSideEffect.emit(LogInSideEffect.NavigateToSignUp)
        }
    }

    fun navigateToMain() {
        viewModelScope.launch {
            _logInSideEffect.emit(LogInSideEffect.NavigateToMain)
        }

    }

}