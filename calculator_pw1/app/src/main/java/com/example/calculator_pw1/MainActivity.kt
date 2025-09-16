package com.example.calculator_pw1


import androidx.compose.foundation.layout.fillMaxSize
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.calculator_pw1.calculation.calculate
import com.example.calculator_pw1.calculation.FormState
import com.example.calculator_pw1.calculation.FormState2
import com.example.calculator_pw1.calculation.calculate2
import com.example.calculator_pw1.ui.theme.Calculator_pw1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Calc1Preview() {
    CalculatorApp()
}


@Preview(showBackground = true)
@Composable
fun Calc2Preview() {
    CalculatorApp2()
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    val formState = remember { mutableStateOf(FormState()) }
    var result by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(text = "PW1.1 Calculator", fontSize = 32.sp)
        NumField(
            labelText = "Hᴾ%",
            value = formState.value.valueHP.value,
            onValueChange = { formState.value.valueHP.value = it }
        )
        NumField(
            labelText = "Cᴾ%",
            value = formState.value.valueCP.value,
            onValueChange = { formState.value.valueCP.value = it }
        )
        NumField(
            labelText = "Sᴾ%",
            value = formState.value.valueSP.value,
            onValueChange = { formState.value.valueSP.value = it }
        )
        NumField(
            labelText = "Nᴾ%",
            value = formState.value.valueNP.value,
            onValueChange = { formState.value.valueNP.value = it }
        )
        NumField(
            labelText = "Oᴾ%",
            value = formState.value.valueOP.value,
            onValueChange = { formState.value.valueOP.value = it }
        )
        NumField(
            labelText = "Wᴾ%",
            value = formState.value.valueWP.value,
            onValueChange = { formState.value.valueWP.value = it }
        )
        NumField(
            labelText = "Aᴾ%",
            value = formState.value.valueAP.value,
            onValueChange = { formState.value.valueAP.value = it }
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
fun CalculatorApp2(modifier: Modifier = Modifier) {
    val formState = remember { mutableStateOf(FormState2()) }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
    ) {
        Text(text = "PW1.2 Calculator", fontSize = 24.sp)
        NumField(
            labelText = "Hᴳ%",
            value = formState.value.valueHG.value,
            onValueChange = { formState.value.valueHG.value = it }
        )
        NumField(
            labelText = "Cᴳ%",
            value = formState.value.valueCG.value,
            onValueChange = { formState.value.valueCG.value = it }
        )
        NumField(
            labelText = "Sᴳ%",
            value = formState.value.valueSG.value,
            onValueChange = { formState.value.valueSG.value = it }
        )
        NumField(
            labelText = "Oᴳ%",
            value = formState.value.valueOG.value,
            onValueChange = { formState.value.valueOG.value = it }
        )
        NumField(
            labelText = "Wᴳ%",
            value = formState.value.valueWG.value,
            onValueChange = { formState.value.valueWG.value = it }
        )
        NumField(
            labelText = "Aᴳ%",
            value = formState.value.valueAG.value,
            onValueChange = { formState.value.valueAG.value = it }
        )
        NumField(
            labelText = "Qᵢ МДж/кг",
            value = formState.value.valueQI.value,
            onValueChange = { formState.value.valueQI.value = it }
        )
        NumField(
            labelText = "Vᴳ МДж/кг",
            value = formState.value.valueVG.value,
            onValueChange = { formState.value.valueVG.value = it }
        )
        FilledTonalButton(onClick = { result = calculate2(formState.value) }) {
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


@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    label = { Text("Calc 1") },
                    icon = { Icon(Icons.Default.ArrowBack, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    label = { Text("Calc 2") },
                    icon = { Icon(Icons.Default.ArrowForward, contentDescription = null) }
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> CalculatorApp(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding))
            1 -> CalculatorApp2(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}