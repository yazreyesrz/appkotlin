package com.ramirinter.lologin.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramirinter.lologin.viewmodel.LocationViewModel
import com.ramirinter.lologin.viewmodel.LocationViewModelFactory

@Composable
fun LocationScreen() {
    val context = LocalContext.current
    val locationViewModel: LocationViewModel = viewModel(factory = LocationViewModelFactory(context))
    val location by locationViewModel.location.collectAsState()
    val errorMessage by locationViewModel.errorMessage.collectAsState()

    val backgroundColor = Color(0xFFEFEFEF) // Gris claro
    val primaryColor = Color(0xFF3B5998) // Azul no tan fuerte
    val secondaryColor = Color(0xFF6D7A8B) // Gris más oscuro para contraste

    LaunchedEffect(Unit) {
        locationViewModel.fetchLocation()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ubicación Actual",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (location != null) {
                    Text(text = "Latitud: ${location!!.latitude}", fontSize = 18.sp, color = secondaryColor)
                    Text(text = "Longitud: ${location!!.longitude}", fontSize = 18.sp, color = secondaryColor)
                } else {
                    Text(text = errorMessage ?: "Ubicación no disponible", fontSize = 18.sp, color = Color.Red)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { locationViewModel.fetchLocation() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Obtener Ubicación", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { (context as? Activity)?.finish() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = secondaryColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Volver", fontSize = 18.sp)
        }
    }
}
