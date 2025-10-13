package com.example.calculator_pw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator_pw4.calculation.*

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
fun Preview() {
    MainScreen()
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
        Text(text = "PW4.1 Calculator", fontSize = 32.sp)
        NumField(
            labelText = "Струм КЗ, Ik",
            value = formState.value.electricCurrent.value,
            onValueChange = { formState.value.electricCurrent.value = it }
        )
        NumField(
            labelText = "Розрахункове навантаження, Sm",
            value = formState.value.load.value,
            onValueChange = { formState.value.load.value = it }
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
            .padding(24.dp)
            .padding(top = 0.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(text = "PW4.2 Calculator", fontSize = 32.sp)
        NumField(
            labelText = "Потужність КЗ, Sk",
            value = formState.value.powerMBA.value,
            onValueChange = { formState.value.powerMBA.value = it }
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
fun CalculatorApp3(modifier: Modifier = Modifier) {
    val formState = remember { mutableStateOf(FormState3()) }
    var result by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .padding(24.dp)
            .padding(top = 0.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(text = "PW4.3 Calculator", fontSize = 32.sp)
        NumField(
            labelText = "Uk.max",
            value = formState.value.resistanceMax.value,
            onValueChange = { formState.value.resistanceMax.value = it }
        )
        NumField(
            labelText = "Ub.h",
            value = formState.value.resistanceBH.value,
            onValueChange = { formState.value.resistanceBH.value = it }
        )
        NumField(
            labelText = "Rc.h",
            value = formState.value.valueRcn.value,
            onValueChange = { formState.value.valueRcn.value = it }
        )
        NumField(
            labelText = "Xc.h",
            value = formState.value.valueXcn.value,
            onValueChange = { formState.value.valueXcn.value = it }
        )
        NumField(
            labelText = "Rc.min",
            value = formState.value.valueRcnMIN.value,
            onValueChange = { formState.value.valueRcnMIN.value = it }
        )
        NumField(
            labelText = "Xc.min",
            value = formState.value.valueXcnMIN.value,
            onValueChange = { formState.value.valueXcnMIN.value = it }
        )


        FilledTonalButton(onClick = { result = calculate3(formState.value) }) {
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
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    label = { Text("Calc 1") },
                    icon = { Text("1️⃣") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    label = { Text("Calc 2") },
                    icon = { Text("2️⃣") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    label = { Text("Calc 3") },
                    icon = { Text("3️⃣") }
                )
            }
        }


    ) { innerPadding ->
        when (selectedTab) {
            0 -> CalculatorApp(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            1 -> CalculatorApp2(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            2 -> CalculatorApp3(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
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