package com.grupo3.smi_mobile_client_android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.grupo3.smi_mobile_client_android.domain.model.Colaborador
import com.grupo3.smi_mobile_client_android.domain.usecase.AuthUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val colaborador: Colaborador? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

class ProfileViewModel(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        cargarPerfil()
    }

    fun retry() {
        cargarPerfil()
    }

    private fun cargarPerfil() {
        val colaborador = authUseCases.getColaboradorActualUseCase()
        _uiState.update {
            it.copy(
                colaborador = colaborador,
                error = if (colaborador == null) "No se encontró información del colaborador" else null,
                isLoading = false
            )
        }
    }
}
