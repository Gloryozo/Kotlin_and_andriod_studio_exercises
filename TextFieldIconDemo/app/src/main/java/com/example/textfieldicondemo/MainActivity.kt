package com.example.textfieldicondemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.textfieldicondemo.ui.component.OutlinedTextFieldWithTrailingIcon
import com.example.textfieldicondemo.ui.theme.TextFieldIconDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldIconDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun DemoApp(modifier: Modifier = Modifier){
    Column (
        modifier = modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        OutlinedTextFieldWithTrailingIcon(label = "User",Icons.Filled.Person,"Person icon")
        OutlinedTextFieldWithTrailingIcon(label = "Password", Icons.Filled.Lock)
      /*  OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {Text("User")},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Person, contentDescription = "User login icon")
                }
            }
        )*/
            }
}


@Preview(showBackground = true)
@Composable
fun TextFieldIconDemoPreview() {
    TextFieldIconDemoTheme {
        DemoApp()
    }
}