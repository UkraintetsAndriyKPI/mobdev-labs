package com.example.calculator_pw3.calculation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.math.E
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

data class FormState(
    var avgDayPower: MutableState<String> = mutableStateOf(""),
    var standardDeviation: MutableState<String> = mutableStateOf(""),
    var electricityPrice: MutableState<String> = mutableStateOf(""),
)


fun calculate(data: FormState): String {


    // Before improvements
    var omegaW1: Double
    var electChangeW11: Double
    var electChangeW12: Double
    var profitP1: Double
    var fine1: Double
    var clearProfitP1: Double
    var conclusion1: String

    // After improvements
    var omegaW2: Double
    var electChangeW21: Double
    var electChangeW22: Double
    var profitP2: Double
    var fine2: Double
    var clearProfitP2: Double
    var conclusion2: String

    try {
        // Before improvements
        omegaW1 = integration(4.75, 5.25, 8, data) / 100
        electChangeW11 = 24 * data.avgDayPower.value.toDouble() * omegaW1
        electChangeW12 = 24 * data.avgDayPower.value.toDouble() * (1 - omegaW1)
        profitP1 = 24 * data.avgDayPower.value.toDouble() * omegaW1 * data.electricityPrice.value.toDouble()
        fine1 = 24 * data.avgDayPower.value.toDouble() * (1 - omegaW1) * data.electricityPrice.value.toDouble()
        clearProfitP1 = profitP1 - fine1
        if (clearProfitP1 > 0) {
            conclusion1 = "Electro station is profitable. Positive profit"
        } else {
            conclusion1 = "Electro station is not profitable. Negative profit"
        }

        // After improvements
        omegaW2 = integration(4.75, 5.25, 26, data) / 100
        electChangeW21 = 24 * data.avgDayPower.value.toDouble() * omegaW2
        electChangeW22 = 24 * data.avgDayPower.value.toDouble() * (1 - omegaW2)
        profitP2 = 24 * data.avgDayPower.value.toDouble() * omegaW2 * data.electricityPrice.value.toDouble()
        fine2 = 24 * data.avgDayPower.value.toDouble() * (1 - omegaW2) * data.electricityPrice.value.toDouble()
        clearProfitP2 = profitP2 - fine2
        if (clearProfitP2 > 0) {
            conclusion2 = "Electro station is profitable. Positive profit"
        } else {
            conclusion2 = "Electro station is not profitable. Negative profit"
        }

    } catch (e: Exception) {
        e.printStackTrace()
        return "Something went wrong."
    }

    return "Before improvements:\n" +
            "δW1 = $omegaW1\n" +
            "W1 = $electChangeW11\n" +
            "W2 = $electChangeW12\n" +
            "profit = $profitP1\n" +
            "fine = $fine1\n" +
            "clear profit = $clearProfitP1\n" +
            "conclusion = $conclusion1\n\n" +

            "After improvements:\n" +
            "δW2 = $omegaW2\n" +
            "W1 = $electChangeW21\n" +
            "W2 = $electChangeW22\n" +
            "profit = $profitP2\n" +
            "fine = $fine2\n" +
            "clear profit = $clearProfitP2\n" +
            "conclusion = $conclusion2\n"
}

fun integration(start: Double, end: Double, steps: Int, data: FormState): Double {
    var sum = 0.0
    val step = (end - start) / steps

    var i = start
    while (i < end) {
        sum += expression(i, data)
        i += step
    }

    return sum
}

fun expression(p: Double, data: FormState): Double {
    return (1 / data.standardDeviation.value.toDouble() * sqrt(2 * PI)) *
            E.pow((p - data.avgDayPower.value.toDouble()).pow(2) / 2 * (data.standardDeviation.value.toDouble().pow(2)))
}
