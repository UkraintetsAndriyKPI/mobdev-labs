package com.example.calculator_pw6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator_pw6.calculation.FormState
import com.example.calculator_pw6.calculation.amountEP
import com.example.calculator_pw6.calculation.calculate
import com.example.calculator_pw6.calculation.calculatedGroupStream
import com.example.calculator_pw6.calculation.getSHRGroupStream
import com.example.calculator_pw6.calculation.getSHRUsageCoeff
import com.example.calculator_pw6.calculation.getSHRnPnKBSUM
import com.example.calculator_pw6.calculation.getSHRnPnKBtgSUM
import com.example.calculator_pw6.calculation.getSHRnPnpow2SUM
import com.example.calculator_pw6.calculation.nPnKB
import com.example.calculator_pw6.calculation.nPnKBtg
import com.example.calculator_pw6.calculation.nPnpow2
import com.example.calculator_pw6.calculation.stream1stLevel
import com.example.calculator_pw6.calculation.usageCoeff
import com.example.calculator_pw6.ui.theme.Calculator_pw6Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    MainScreen()
//}


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
        Text(text = "PW6 Calculator", fontSize = 32.sp)
        NumField(
            labelText = "Шліфувальний верст. - номінал. потужність ЕП: Рн, кВТ",
            value = formState.value.nominalPowerGrindingMachine.value,
            onValueChange = { formState.value.nominalPowerGrindingMachine.value = it }
        )
        NumField(
            labelText = "Полірувальний верст. - коеф. використання: Кв",
            value = formState.value.usageCoeffPolishingMachine.value,
            onValueChange = { formState.value.usageCoeffPolishingMachine.value = it }
        )
        NumField(
            labelText = "Циркулярна пила - коеф. реак. потужності: tgφ",
            value = formState.value.reactivePowerCoeffCircularSaw.value,
            onValueChange = { formState.value.reactivePowerCoeffCircularSaw.value = it }
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
fun TableApp(modifier: Modifier = Modifier) {


    Column(
        modifier = modifier
            .padding(12.dp)
            .padding(top = 0.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(text = "PW6 Result Table", fontSize = 32.sp)

        ScrollableZebraTable()
//        if (result.isNotEmpty()) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                    .verticalScroll(rememberScrollState())
//                    .padding(8.dp)
//            ) {
//                Text(
//                    text = "Result:\n$result",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .clip(RoundedCornerShape(12.dp))
//                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
//                        .padding(8.dp)
//                        .fillMaxSize()
//                )
//            }
//        }
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
                    label = { Text("Calculator") },
                    icon = { Text("1️⃣") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    label = { Text("Table") },
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

            1 -> TableApp(
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


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Calculator_pw6Theme {
        MainScreen()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TablePreview() {
//    Calculator_pw6Theme {
//        TableApp()
//    }
//}


@Composable
fun ScrollableZebraTable() {
    val columns = listOf(
        "Назва ЕП", "К-сть ЕП: n, шт", "Струми на I рівні: Ipr, A", "Груповий коеф. використання: Кв",
        "Розрахункові групові струми: Iр А", "n*Рн*Кв, кВт", "n*Рн*Кв*tgφ, квар", "n*Рн^2",
    )
    val shrAmount = amountEP.values.sum() - amountEP["WeldingTransformer"]!! - amountEP["DryerWardrobe"]!!
    val shrstream1stLevel = stream1stLevel.values.sum() - stream1stLevel["WeldingTransformer"]!! - stream1stLevel["DryerWardrobe"]!!
    val shrUsageCoeff = getSHRUsageCoeff()
    val shrnPnKBSUM = getSHRnPnKBSUM()
    val shrnPnKBtgSUM = getSHRnPnKBtgSUM()
    val shrnPnpow2SUM = getSHRnPnpow2SUM()

    val shrGroupStream = getSHRGroupStream(shrnPnKBSUM*1.25)

    val data = listOf(
        listOf(
            "Шліфувальний верстат",
            amountEP["GrindingMachine"],
            stream1stLevel["GrindingMachine"],
            usageCoeff["GrindingMachine"],
            calculatedGroupStream["GrindingMachine"],
            nPnKB["GrindingMachine"],
            nPnKBtg["GrindingMachine"],
            nPnpow2["GrindingMachine"],
        ),
        listOf(
            "Свердлильний верстат",
            amountEP["DrillingMachine"],
            stream1stLevel["DrillingMachine"],
            usageCoeff["DrillingMachine"],
            calculatedGroupStream["DrillingMachine"],
            nPnKB["DrillingMachine"],
            nPnKBtg["DrillingMachine"],
            nPnpow2["DrillingMachine"],
        ),
        listOf(
            "Фугувальний верстат",
            amountEP["GroutingMachine"],
            stream1stLevel["GroutingMachine"],
            usageCoeff["GroutingMachine"],
            calculatedGroupStream["GroutingMachine"],
            nPnKB["GroutingMachine"],
            nPnKBtg["GroutingMachine"],
            nPnpow2["GroutingMachine"],
        ),
        listOf(
            "Циркулярна пила",
            amountEP["CircularSaw"],
            stream1stLevel["CircularSaw"],
            usageCoeff["CircularSaw"],
            calculatedGroupStream["CircularSaw"],
            nPnKB["CircularSaw"],
            nPnKBtg["CircularSaw"],
            nPnpow2["CircularSaw"],
        ),
        listOf(
            "Пресс",
            amountEP["Press"],
            stream1stLevel["Press"],
            usageCoeff["Press"],
            calculatedGroupStream["Press"],
            nPnKB["Press"],
            nPnKBtg["Press"],
            nPnpow2["Press"],
        ),
        listOf(
            "Полірувальний верстат",
            amountEP["PolishingMachine"],
            stream1stLevel["PolishingMachine"],
            usageCoeff["PolishingMachine"],
            calculatedGroupStream["PolishingMachine"],
            nPnKB["PolishingMachine"],
            nPnKBtg["PolishingMachine"],
            nPnpow2["PolishingMachine"],
        ),
        listOf(
            "Фрезерний верстат",
            amountEP["MillingMachine"],
            stream1stLevel["MillingMachine"],
            usageCoeff["MillingMachine"],
            calculatedGroupStream["MillingMachine"],
            nPnKB["MillingMachine"],
            nPnKBtg["MillingMachine"],
            nPnpow2["MillingMachine"],
        ),
        listOf(
            "Вентилятор",
            amountEP["Fan"],
            stream1stLevel["Fan"],
            usageCoeff["Fan"],
            calculatedGroupStream["Fan"],
            nPnKB["Fan"],
            nPnKBtg["Fan"],
            nPnpow2["Fan"],
        ),

        listOf("Всього ШР1", "$shrAmount", "$shrstream1stLevel", "$shrUsageCoeff", "$shrGroupStream", "$shrnPnKBSUM", "$shrnPnKBtgSUM", "$shrnPnpow2SUM",),
        listOf("Всього ШР2", "$shrAmount", "$shrstream1stLevel", "$shrUsageCoeff", "$shrGroupStream", "$shrnPnKBSUM", "$shrnPnKBtgSUM", "$shrnPnpow2SUM",),
        listOf("Всього ШР3", "$shrAmount", "$shrstream1stLevel", "$shrUsageCoeff", "$shrGroupStream", "$shrnPnKBSUM", "$shrnPnKBtgSUM", "$shrnPnpow2SUM",),

        listOf(
            "Зварювальний трансформатор",
            amountEP["WeldingTransformer"],
            stream1stLevel["WeldingTransformer"],
            usageCoeff["WeldingTransformer"],
            calculatedGroupStream["WeldingTransformer"],
            nPnKB["WeldingTransformer"],
            nPnKBtg["WeldingTransformer"],
            nPnpow2["WeldingTransformer"],
        ),
        listOf(
            "Сушильна шафа",
            amountEP["DryerWardrobe"],
            stream1stLevel["DryerWardrobe"],
            usageCoeff["DryerWardrobe"],
            calculatedGroupStream["DryerWardrobe"],
            nPnKB["DryerWardrobe"],
            "-",
            nPnpow2["DryerWardrobe"],
        )
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()) // вертикальний скрол
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFFDDDDDD))
                    .fillMaxWidth()
            ) {
                columns.forEach { header ->
                    Text(
                        text = header,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .width(130.dp)
                            .padding(6.dp)
                    )
                }
            }

            data.forEachIndexed { index, row ->
                val backgroundColor =
                    if (index % 2 == 0) Color(0xFFF7F7F7) // світлий рядок
                    else Color(0xFFFFFFFF)               // білий рядок

                Row(
                    modifier = Modifier
                        .background(backgroundColor)
                        .fillMaxWidth()
                ) {
                    row.forEach { cell ->
                        Text(
                            text = "$cell",
                            modifier = Modifier
                                .width(130.dp)
                                .padding(6.dp)
                        )
                    }
                }
            }
        }
    }
}

