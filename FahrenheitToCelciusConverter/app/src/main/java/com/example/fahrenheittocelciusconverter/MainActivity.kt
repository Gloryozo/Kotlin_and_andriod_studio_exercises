package com.example.fahrenheittocelciusconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fahrenheittocelciusconverter.ui.theme.FahrenheitToCelciusConverterTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FahrenheitToCelciusConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   FahrenheitToCelsius(
                    modifier = Modifier.padding(innerPadding)
                    )
                }
            }

        }
    }
}

@Composable
fun  FahrenheitToCelsius(modifier: Modifier = Modifier) {
    var temperature: String by remember { mutableStateOf("") }
    var fahrenheitSelected: Boolean by remember { mutableStateOf(true)}
    val temperatureFloatVal: Float = temperature.toFloatOrNull() ?: 0.0f
    val result = when (fahrenheitSelected) {
        true -> if (temperatureFloatVal > 0.0f) (temperatureFloatVal - 32) / 1.8f
        else 0.0f
        false -> if (temperatureFloatVal > 0.0f) (temperatureFloatVal * 1.8f) + 32
        else 0.0f
    }
    val df = DecimalFormat("#.##")
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text="Fahrenheit/Celsius",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
    )

        OutlinedTextField(
          modifier = Modifier.fillMaxWidth(),
            value = temperature,
            onValueChange = { temperature = it },
            label = {Text(text = "Temperature")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row (verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = fahrenheitSelected,
                onClick = {fahrenheitSelected = true}
            )
            Text(text = "Fahrenheit to Celsius")
        }
        Row (verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = !fahrenheitSelected,
                onClick = { fahrenheitSelected = false }
            )
            Text(text = "Celsius to Fahrenheit")
        }
        Text(text = df.format(result))

    }

}

@Preview(showBackground = true)
@Composable
fun FahrenheitToCelsiusPreview() {
    FahrenheitToCelciusConverterTheme {
        FahrenheitToCelsius()
    }
}