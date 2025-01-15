package com.example.glorysapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glorysapplication.ui.theme.GlorysApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlorysApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrencyScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
//modifiers are used for styling the app
fun CurrencyScreen(modifier: Modifier = Modifier) {
    var euros by remember { mutableStateOf("") }
val eurosAsNumber = euros.toFloatOrNull() ?: 0.0f
val naira = eurosAsNumber * 1582.10

    val fieldmodifier: Modifier = modifier.fillMaxWidth()
    Column (
        modifier = modifier.fillMaxWidth().padding(all =8.dp),
        ){
        Text(
        text = "Euros to Naira",
        modifier = fieldmodifier,
            textAlign = TextAlign.Center,
            fontSize =24.sp
        )
        Text(
            text = "Enter euros"
        )
        TextField(
            value= euros,
            onValueChange = {euros = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = fieldmodifier
        )
        Text(
            modifier = fieldmodifier,
            text = "Naira"
        )
        Text(
            modifier = fieldmodifier,
            text = naira.toString()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyPreview() {
    GlorysApplicationTheme {
        CurrencyScreen()
    }
}