package com.example.calculator_pw4.calculation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.math.pow

data class FormState(
    var electricCurrent: MutableState<String> = mutableStateOf(""),
    var load: MutableState<String> = mutableStateOf(""),

)

data class FormState2(
    var powerMBA: MutableState<String> = mutableStateOf(""),
)

data class FormState3(
    var resistanceMax: MutableState<String> = mutableStateOf(""),
    var resistanceBH: MutableState<String> = mutableStateOf(""),
    var valueRcn: MutableState<String> = mutableStateOf(""),
    var valueXcn: MutableState<String> = mutableStateOf(""),
    var valueRcnMIN: MutableState<String> = mutableStateOf(""),
    var valueXcnMIN: MutableState<String> = mutableStateOf(""),
)

fun calculate(data: FormState): String {
    val voltage = 10
    val workTimeSec = 2.5
    val power = 2 * 1000
    val timeHourLoad = 4000

    try {
        val normalModeCurrent = data.load.value.toDouble() / (3.0.pow(0.5) * 2 * voltage)
        val emergencyModeCurrent = 2 * normalModeCurrent
        val sek = normalModeCurrent / 1.4
        val smin = data.electricCurrent.value.toDouble() * 1000

        return "Тип: ААБ кабель.\n" +
                "Струм за норм. режимом: $normalModeCurrent\n" +
                "Струм за авар. режимом: $emergencyModeCurrent\n" +
                "Економічний переріз: $sek\n"

    } catch (e: Exception) {

        e.printStackTrace()
        return "Something went wrong."
    }
}

fun calculate2(data: FormState2): String {
    val voltage = 10.5

    try {
        val resistanceElementsXc = voltage.pow(2) / data.powerMBA.value.toDouble()
        val resistanceElementsXt = voltage / 100 * voltage.pow(2) / 6.3
        val resistanceSum = resistanceElementsXc + resistanceElementsXt
        val startCurrent = voltage / (3.0.pow(0.5) * resistanceSum)

        return "Опори елем. заступної схеми: Xc = $resistanceElementsXc\n" +
                "Опори елем. заступної схеми: Xт = $resistanceElementsXt\n" +
                "Сумарний опір для точки К1: Xsum = $resistanceSum\n" +
                "Початкове знач. струму трифазного КЗ: Iп0 = $startCurrent\n"
    } catch (e: Exception) {
        e.printStackTrace()
        return "Something went wrong."
    }
}

fun calculate3(data: FormState3): String {

    try {
        val reactiveResistance =
            (data.resistanceMax.value.toDouble() * data.resistanceBH.value.toDouble().pow(2)) / (100 * 6.3)

        val busResistanceX = data.valueXcn.value.toDouble() + reactiveResistance
        val busResistanceZ = (data.valueRcn.value.toDouble().pow(2) + busResistanceX.pow(2)).pow(0.5)

        val busResistanceXmin = data.valueXcnMIN.value.toDouble() + reactiveResistance
        val busResistanceZmin = (data.valueRcnMIN.value.toDouble().pow(2) + busResistanceXmin.pow(2)).pow(0.5)

        val stream3 = (data.resistanceBH.value.toDouble() * 1000) / (3.0.pow(0.5) * busResistanceZ)
        val stream2 = stream3 * (3.0.pow(0.5)) / 2
        val stream3min = (data.resistanceBH.value.toDouble() * 1000) / (3.0.pow(0.5) * busResistanceZmin)
        val stream2min = stream3min * (3.0.pow(0.5)) / 2

        return "Реактивний опірсилового трансформ.: Xт = $reactiveResistance\n" +
                "Струми 3та2 фазного КЗ на шинах 10кВ в норм. режимі: Iш(3) = $stream3\n" +
                "Струми 3та2 фазного КЗ на шинах 10кВ в норм. режимі: Iш(2) = $stream2\n" +
                "Струми 3та2 фазного КЗ на шинах 10кВ в мін. режимі: Iш.min(3) = $stream3min\n" +
                "Струми 3та2 фазного КЗ на шинах 10кВ в мін. режимі: Iш.min(2) = $stream2min\n"
    } catch (e: Exception) {

        e.printStackTrace()
        return "Something went wrong."
    }
}
