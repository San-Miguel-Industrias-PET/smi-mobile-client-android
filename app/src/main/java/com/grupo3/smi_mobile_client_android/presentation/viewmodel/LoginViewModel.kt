package com.grupo3.smi_mobile_client_android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.smi_mobile_client_android.domain.usecase.AuthUseCases
import com.grupo3.smi_mobile_client_android.presentation.event.EventBus
import com.grupo3.smi_mobile_client_android.presentation.event.UiEvent
import com.grupo3.smi_mobile_client_android.presentation.screens.login.LoginUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome: SharedFlow<Unit> = _navigateToHome.asSharedFlow()

    fun onDniChange(value: String) {
        _uiState.update { it.copy(dni = value) }
    }

    fun onCredencialChange(value: String) {
        _uiState.update { it.copy(credencial = value) }
    }

    fun iniciarSesion() {
        val dni = _uiState.value.dni.trim()
        val credencial = _uiState.value.credencial

        viewModelScope.launch {
            if (dni.isBlank() || credencial.isBlank()) {
                EventBus.emit(UiEvent.Warning("Ingresa tu DNI y tu credencial corporativa"))
                return@launch
            }

            _uiState.update { it.copy(isLoading = true) }
            try {
                authUseCases.loginUseCase(dni, credencial)
                EventBus.emit(UiEvent.Success("Login exitoso"))
                delay(NAVIGATE_DELAY_MS)
                _navigateToHome.emit(Unit)
            } catch (e: Exception) {
                EventBus.emit(UiEvent.Error(e.message ?: "No se pudo iniciar sesión"))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private companion object {
        const val NAVIGATE_DELAY_MS = 700L
    }
}
