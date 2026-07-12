package com.grupo3.smi_mobile_client_android.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupo3.smi_mobile_client_android.presentation.components.AppLogo
import com.grupo3.smi_mobile_client_android.presentation.components.AppScaffold
import com.grupo3.smi_mobile_client_android.presentation.viewmodel.LoginViewModel
import com.grupo3.smi_mobile_client_android.ui.theme.SmiBorder
import com.grupo3.smi_mobile_client_android.ui.theme.SmiPlaceholder
import com.grupo3.smi_mobile_client_android.ui.theme.SmiRed
import com.grupo3.smi_mobile_client_android.ui.theme.SmiSurfaceWhite
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextPrimary
import com.grupo3.smi_mobile_client_android.ui.theme.SmiTextSecondary
import com.grupo3.smi_mobile_client_android.ui.theme.SmimobileclientandroidTheme

@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginSuccess: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigateToHome.collect { onLoginSuccess() }
    }

    AppScaffold { innerPadding ->
        LoginContent(
            modifier = Modifier.padding(innerPadding),
            dni = uiState.dni,
            credencial = uiState.credencial,
            isLoading = uiState.isLoading,
            onDniChange = viewModel::onDniChange,
            onCredencialChange = viewModel::onCredencialChange,
            onIniciarSesion = viewModel::iniciarSesion
        )
    }
}

@Composable
private fun LoginContent(
    dni: String,
    credencial: String,
    isLoading: Boolean,
    onDniChange: (String) -> Unit,
    onCredencialChange: (String) -> Unit,
    onIniciarSesion: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(SmiRed)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SmiSurfaceWhite)
                    .border(1.dp, SmiBorder, RoundedCornerShape(16.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(SmiRed)
                )

                Column(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppLogo(modifier = Modifier.size(92.dp))

                    Spacer(Modifier.height(20.dp))

                    Text(
                        text = "Bienvenido a SMI Connect",
                        color = SmiTextPrimary,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "Ingresa tus credenciales para acceder",
                        color = SmiTextSecondary,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(24.dp))

                    FormLabel(text = "DOCUMENTO DE IDENTIDAD (DNI)")
                    Spacer(Modifier.height(6.dp))
                    OutlinedTextField(
                        value = dni,
                        onValueChange = onDniChange,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        enabled = !isLoading,
                        placeholder = { Text("Ej: 12345678", color = SmiPlaceholder) },
                        leadingIcon = {
                            Icon(Icons.Default.Badge, contentDescription = null, tint = SmiPlaceholder)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(10.dp),
                        colors = smiTextFieldColors()
                    )

                    Spacer(Modifier.height(16.dp))

                    FormLabel(text = "CREDENCIAL CORPORATIVA")
                    Spacer(Modifier.height(6.dp))
                    OutlinedTextField(
                        value = credencial,
                        onValueChange = onCredencialChange,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        enabled = !isLoading,
                        placeholder = { Text("Ingresa tu clave", color = SmiPlaceholder) },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = SmiPlaceholder)
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(10.dp),
                        colors = smiTextFieldColors()
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = onIniciarSesion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        enabled = !isLoading,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SmiRed,
                            contentColor = Color.White,
                            disabledContainerColor = SmiRed.copy(alpha = 0.6f),
                            disabledContentColor = Color.White
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                text = "INICIAR SESIÓN",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                letterSpacing = 0.5.sp
                            )
                            Spacer(Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                    HorizontalDivider(color = SmiBorder)
                    Spacer(Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                            contentDescription = null,
                            tint = SmiTextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle(color = SmiTextSecondary)) {
                                    append("Si tienes problemas para ingresar, ")
                                }
                                withStyle(SpanStyle(color = SmiRed, fontWeight = FontWeight.Bold)) {
                                    append("contacta a TI")
                                }
                            },
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FormLabel(text: String) {
    Text(
        text = text,
        color = SmiTextPrimary,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun smiTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = SmiRed,
    unfocusedBorderColor = SmiBorder,
    focusedContainerColor = SmiSurfaceWhite,
    unfocusedContainerColor = SmiSurfaceWhite,
    cursorColor = SmiRed,
    focusedTextColor = SmiTextPrimary,
    unfocusedTextColor = SmiTextPrimary
)

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    SmimobileclientandroidTheme {
        LoginContent(
            dni = "",
            credencial = "",
            isLoading = false,
            onDniChange = {},
            onCredencialChange = {},
            onIniciarSesion = {}
        )
    }
}
