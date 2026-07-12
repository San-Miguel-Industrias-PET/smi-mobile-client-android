package com.grupo3.smi_mobile_client_android.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.grupo3.smi_mobile_client_android.ui.theme.SmiRed
import com.grupo3.smi_mobile_client_android.ui.theme.SmiSuccess
import com.grupo3.smi_mobile_client_android.ui.theme.SmiWarning

data class UiEventSnackbarVisuals(
    override val message: String,
    val status: SnackbarStatus,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short
) : SnackbarVisuals

@Composable
fun SnackbarCustom(
    data: SnackbarData,
    status: SnackbarStatus,
    modifier: Modifier = Modifier
) {
    val containerColor = when (status) {
        SnackbarStatus.SUCCESS -> SmiSuccess
        SnackbarStatus.ERROR -> SmiRed
        SnackbarStatus.WARNING -> SmiWarning
    }
    val icon = when (status) {
        SnackbarStatus.SUCCESS -> Icons.Default.CheckCircle
        SnackbarStatus.ERROR -> Icons.Default.ErrorOutline
        SnackbarStatus.WARNING -> Icons.Default.Warning
    }

    Snackbar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = Color.White,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text(text = data.visuals.message)
        }
    }
}
