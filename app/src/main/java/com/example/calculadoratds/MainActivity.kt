package com.example.calculadoratds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoratds.ui.theme.CalculadoraTDSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTDSTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf<Char>(' ') }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1", color = Color.Gray) },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2", color = Color.Gray) },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OperationButton("+", operation) { operation = '+' }
            OperationButton("-", operation) { operation = '-' }
            OperationButton("*", operation) { operation = '*' }
            OperationButton("/", operation) { operation = '/' }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = when (operation) {
                    '+' -> (num1.toDoubleOrNull() ?: 0.0) + (num2.toDoubleOrNull() ?: 0.0)
                    '-' -> (num1.toDoubleOrNull() ?: 0.0) - (num2.toDoubleOrNull() ?: 0.0)
                    '*' -> (num1.toDoubleOrNull() ?: 0.0) * (num2.toDoubleOrNull() ?: 0.0)
                    '/' -> {
                        if (num2.toDoubleOrNull() != 0.0) {
                            (num1.toDoubleOrNull() ?: 0.0) / (num2.toDoubleOrNull() ?: 1.0)
                        } else {
                            Double.NaN
                        }
                    }
                    else -> Double.NaN
                }.toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Resultado: $result",
            color = Color.Gray
        )
    }
}

@Composable
fun OperationButton(text: String, currentOperation: Char, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(60.dp, 60.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = if (text[0] == currentOperation) Color(0xFF6200EE) else Color.Gray
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculadoraTDSTheme {
        Calculator()
    }
}
