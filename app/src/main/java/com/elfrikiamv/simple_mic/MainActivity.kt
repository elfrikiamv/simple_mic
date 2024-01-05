package com.elfrikiamv.simple_mic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication()
        }
    }
}

@Preview
@Composable
fun MyApplication() {
    // Estado para el indicador de conexión
    var isConnected by remember { mutableStateOf(false) }
    // Estado para la dirección IP del dispositivo
    var ipAddress by remember { mutableStateOf(TextFieldValue("")) }
    // Estado para el control de volumen del micrófono
    var microphoneVolume by remember { mutableStateOf(0.5f) }

    // Diseño principal de la aplicación
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título del estado de la conexión
            Text(
                text = "Estado de la Conexión",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Indicador de conexión
            ConnectionIndicator(isConnected = isConnected)

            Spacer(modifier = Modifier.height(32.dp))

            // Título de la dirección IP
            Text(
                text = "Dirección IP del Dispositivo",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para la dirección IP
            BasicTextField(
                value = ipAddress,
                onValueChange = {
                    ipAddress = it
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Control de volumen del micrófono
            MicrophoneVolumeControl(
                volume = microphoneVolume,
                onVolumeChange = {
                    microphoneVolume = it
                }
            )
        }
    }
}

@Composable
fun ConnectionIndicator(isConnected: Boolean) {
    // Color del texto basado en el estado de la conexión
    val color = if (isConnected) Color.Green else Color.Red
    // Texto a mostrar basado en el estado de la conexión
    val text = if (isConnected) "Conectado" else "Desconectado"

    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodySmall,
        fontSize = 24.sp
    )
}

@Composable
fun MicrophoneVolumeControl(
    volume: Float,
    onVolumeChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título del control de volumen del micrófono
        Text(
            text = "Control de Volumen del Micrófono",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Slider para el control de volumen
        Slider(
            value = volume,
            onValueChange = {
                onVolumeChange(it)
            },
            valueRange = 0f..1f,
            steps = 100,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto que muestra el valor del volumen
        Text(
            text = "Volumen: ${(volume * 100).toInt()}%",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}