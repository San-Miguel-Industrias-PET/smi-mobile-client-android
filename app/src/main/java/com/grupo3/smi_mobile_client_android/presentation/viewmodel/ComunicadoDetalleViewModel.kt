package com.grupo3.smi_mobile_client_android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo3.smi_mobile_client_android.domain.model.Comunicado
import com.grupo3.smi_mobile_client_android.domain.usecase.ComunicadoUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ComunicadoDetalleUiState(
    val comunicado: Comunicado? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

class ComunicadoDetalleViewModel(
    private val comunicadoUseCases: ComunicadoUseCases,
    private val id: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(ComunicadoDetalleUiState())
    val uiState: StateFlow<ComunicadoDetalleUiState> = _uiState.asStateFlow()

    init {
        cargarDetalle()
    }

    fun retry() {
        cargarDetalle()
    }

    private fun cargarDetalle() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val comunicado = comunicadoUseCases.getNoticiaDetalleUseCase(id)
                _uiState.update { it.copy(comunicado = comunicado) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Ocurrió un error inesperado") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
