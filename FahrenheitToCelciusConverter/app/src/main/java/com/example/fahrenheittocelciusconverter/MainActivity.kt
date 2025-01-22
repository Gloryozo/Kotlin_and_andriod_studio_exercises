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
        super.onCreate(savedInstanceState) // Call the superclass method
        enableEdgeToEdge() // Enable edge-to-edge display
        setContent { // Set the content of the activity
            FahrenheitToCelciusConverterTheme { // Apply the theme
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // Create a scaffold layout
                    FahrenheitToCelsius( // Call the composable function to display the converter
                        modifier = Modifier.padding(innerPadding) // Apply padding
                    )
                }
            }
        }
    }
}

@Composable
fun FahrenheitToCelsius(modifier: Modifier = Modifier) {
    var temperature: String by remember { mutableStateOf("") } // State for temperature input
    var fahrenheitSelected: Boolean by remember { mutableStateOf(true) } // State to track selected conversion type
    val temperatureFloatVal: Float = temperature.toFloatOrNull() ?: 0.0f // Convert input to Float, default to 0.0f
    val result = when (fahrenheitSelected) { // Determine conversion result based on selected type
        true -> if (temperatureFloatVal > 0.0f) (temperatureFloatVal - 32) / 1.8f // Fahrenheit to Celsius
        else 0.0f
        false -> if (temperatureFloatVal > 0.0f) (temperatureFloatVal * 1.8f) + 32 // Celsius to Fahrenheit
        else 0.0f
    }
    val df = DecimalFormat("#.##") // Create a DecimalFormat instance for formatting the result
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between elements
        modifier = modifier.padding(8.dp) // Apply padding to the column
    ) {
        Text(
            text = "Fahrenheit/Celsius", // Title text
            color = MaterialTheme.colorScheme.primary, // Primary color from theme
            fontSize = MaterialTheme.typography.titleLarge.fontSize, // Font size from theme
            modifier = Modifier.fillMaxWidth(), // Fill the width
            textAlign = TextAlign.Center // Center the text
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(), // Fill the width of the text field
            value = temperature, // Bind the text field to temperature state
            onValueChange = { temperature = it }, // Update state on value change
            label = { Text(text = "Temperature") }, // Label for the text field
            singleLine = true, // Single line input
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Numeric keyboard
        )
        Row(verticalAlignment = Alignment.CenterVertically) { // Row for Fahrenheit to Celsius option
            RadioButton(
                selected = fahrenheitSelected, // Check if this option is selected
                onClick = { fahrenheitSelected = true } // Update state on click
            )
            Text(text = "Fahrenheit to Celsius") // Option label
        }
        Row(verticalAlignment = Alignment.CenterVertically) { // Row for Celsius to Fahrenheit option
            RadioButton(
                selected = !fahrenheitSelected, // Check if this option is selected
                onClick = { fahrenheitSelected = false } // Update state on click
            )
            Text(text = "Celsius to Fahrenheit") // Option label
        }
        Text(text = df.format(result)) // Display the formatted result

    }
}

@Preview(showBackground = true)
@Composable
fun FahrenheitToCelsiusPreview() {
    FahrenheitToCelciusConverterTheme { // Preview with the applied theme
        FahrenheitToCelsius() // Call the composable function for preview
    }
}
