package com.grupo3.smi_mobile_client_android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupo3.smi_mobile_client_android.ui.theme.SmiBorder
import com.grupo3.smi_mobile_client_android.ui.theme.SmiRed
import com.grupo3.smi_mobile_client_android.ui.theme.SmiSurfaceWhite

// Usa res/drawable/logo (jpg/png/webp) si el equipo de diseño lo agrega; si no existe, cae al monograma "SMI".
@Composable
fun AppLogo(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val logoResId = remember {
        context.resources.getIdentifier("logo", "drawable", context.packageName)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SmiSurfaceWhite)
            .border(1.dp, SmiBorder, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (logoResId != 0) {
            Image(
                painter = painterResource(id = logoResId),
                contentDescription = "Logo de SMI",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )
        } else {
            Text(
                text = "SMI",
                color = SmiRed,
                fontWeight = FontWeight.Black,
                fontSize = 24.sp
            )
        }
    }
}
