package com.grupo3.smi_mobile_client_android.presentation.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventBus {
    private val _events = MutableSharedFlow<UiEvent>()
    val events: SharedFlow<UiEvent> = _events.asSharedFlow()

    suspend fun emit(event: UiEvent) {
        _events.emit(event)
    }
}
