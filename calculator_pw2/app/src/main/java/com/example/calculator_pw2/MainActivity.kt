package com.example.calculator_pw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator_pw2.calculation.FormState
import com.example.calculator_pw2.calculation.calculate
import com.example.calculator_pw2.ui.theme.Calculator_pw2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalcPreview() {
    CalculatorApp()
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    val formState = remember { mutableStateOf(FormState()) }
    var result by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .padding(24.dp)
            .padding(top = 0.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(text = "PW2 Calculator", fontSize = 32.sp)
        NumField(
            labelText = "Вугілля марки ГР, т",
            value = formState.value.coalAmount.value,
            onValueChange = { formState.value.coalAmount.value = it }
        )
        NumField(
            labelText = "Мазут марки 40, т",
            value = formState.value.oilAmount.value,
            onValueChange = { formState.value.oilAmount.value = it }
        )
        NumField(
            labelText = "Газ із газопроводу Уренгой-Ужгород, м3",
            value = formState.value.gasAmount.value,
            onValueChange = { formState.value.gasAmount.value = it }
        )

        FilledTonalButton(onClick = { result = calculate(formState.value) }) {
            Text("Calculate")
        }

        if (result.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                Text(
                    text = "Result:\n$result",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
                        .padding(8.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun NumField(
    modifier: Modifier = Modifier,
    labelText: String = "",
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        onValueChange = onValueChange,
        label = { Text(labelText) }
    )
}