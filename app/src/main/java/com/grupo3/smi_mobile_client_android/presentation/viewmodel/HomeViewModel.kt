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

data class HomeUiState(
    val noticias: List<Comunicado> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val categoriaSeleccionada: String = "Todos"
)

class HomeViewModel(
    private val comunicadoUseCases: ComunicadoUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        cargarNoticias()
    }

    fun onCategoriaSeleccionada(categoria: String) {
        _uiState.update { it.copy(categoriaSeleccionada = categoria) }
        cargarNoticias()
    }

    fun retry() {
        cargarNoticias()
    }

    private fun cargarNoticias() {
        val categoria = _uiState.value.categoriaSeleccionada
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val noticias = comunicadoUseCases.getNoticiasUseCase(categoria)
                _uiState.update { it.copy(noticias = noticias) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Ocurrió un error inesperado") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
