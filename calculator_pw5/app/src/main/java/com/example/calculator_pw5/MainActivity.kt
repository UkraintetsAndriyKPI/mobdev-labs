package com.example.calculator_pw5

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
import com.example.calculator_pw5.calculation.*

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
        Text(text = "PW5.1 Calculator", fontSize = 32.sp)
        NumField(
            labelText = "Кількість елегазових вимикачів 110kB, шт",
            value = formState.value.electricGasSwitchAmount.value,
            onValueChange = { formState.value.electricGasSwitchAmount.value = it }
        )
        NumField(
            labelText = "Довжина ПЛ-110 kB, км",
            value = formState.value.electricPL100kBlen.value,
            onValueChange = { formState.value.electricPL100kBlen.value = it }
        )
        NumField(
            labelText = "К-сть трансформаторів 110/10kB, шт",
            value = formState.value.converter110kBAmount.value,
            onValueChange = { formState.value.converter110kBAmount.value = it }
        )
        NumField(
            labelText = "Ввідні вимикачі 10kB, шт",
            value = formState.value.switcher10kBAmount.value,
            onValueChange = { formState.value.switcher10kBAmount.value = it }
        )
        NumField(
            labelText = "Приєднання 10kB, шт",
            value = formState.value.connector10kBamount.value,
            onValueChange = { formState.value.connector10kBamount.value = it }
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
        Text(text = "PW5.2 Calculator", fontSize = 32.sp)

        NumField(
            labelText = "Збидки при аварійному вимкненні, грн/кВт*год",
            value = formState.value.accidentCost.value,
            onValueChange = { formState.value.accidentCost.value = it }
        )
        NumField(
            labelText = "Збидки при плановому вимкненні, грн/кВт*год",
            value = formState.value.scheduleCost.value,
            onValueChange = { formState.value.scheduleCost.value = it }
        )
        NumField(
            labelText = "Частота відмов, рік^-1",
            value = formState.value.denyRate.value,
            onValueChange = { formState.value.denyRate.value = it }
        )
        NumField(
            labelText = "Серед. час відновлення трансформатора, tB",
            value = formState.value.avgAccidentDenyTine.value,
            onValueChange = { formState.value.avgAccidentDenyTine.value = it }
        )
        NumField(
            labelText = "Серед. час планованого простою трансформатора, kn",
            value = formState.value.avgScheduleDenyTine.value,
            onValueChange = { formState.value.avgScheduleDenyTine.value = it }
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