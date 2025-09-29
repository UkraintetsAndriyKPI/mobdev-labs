package com.example.calculator_pw2.calculation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.lang.Math.pow
import kotlin.math.pow

data class FormState(
    var coalAmount: MutableState<String> = mutableStateOf(""),
    var oilAmount: MutableState<String> = mutableStateOf(""),
    var gasAmount: MutableState<String> = mutableStateOf(""),
)


fun calculate(data: FormState): String {

    val nCleaning = 0.985

    val qrCoal = 20.47
    val qrOil = 39.48
    val qrGas = 33.08

    val aOutCoal = 0.8
    val aOutOil = 1
    val aOutGas = 0

    val AInCoal = 25.20
    val AInOil = 0.15
    val AInGas = 0

    val GoutCoal = 1.5
    val GoutOil = 0
    val GoutGas = 0

    var coalKTB: Double
    var coalETB: Double

    var oilKTB: Double
    var oilETB: Double

    var gasKTB: Double
    var gasETB: Double



    try {
        coalKTB = (10.0.pow(6) / qrCoal) * aOutCoal * (AInCoal / (100 - GoutCoal)) * (1 - nCleaning)
        coalETB = 10.0.pow(-6) * coalKTB * qrCoal * data.coalAmount.value.toDouble()

        oilKTB = (10.0.pow(6) / qrOil) * aOutOil * (AInOil / (100 - GoutOil)) * (1 - nCleaning)
        oilETB = 10.0.pow(-6) * oilKTB * qrOil * data.oilAmount.value.toDouble()

        gasKTB = (10.0.pow(6) / qrGas) * aOutGas * (AInGas / (100 - GoutGas)) * (1 - nCleaning)
        gasETB = 10.0.pow(-6) * gasKTB * qrGas * data.gasAmount.value.toDouble()
    } catch (e: Exception) {
        e.printStackTrace()
        return "Something went wrong."
    }

    return  "Вугілля:\n" +
            "kₜᵦ = $coalKTB\n" +
            "Eₜᵦ = $coalETB\n" +

            "Мазута:\n" +
            "kₜᵦ = $oilKTB\n" +
            "Eₜᵦ = $oilETB\n" +

            "Газ:\n" +
            "kₜᵦ = $gasKTB\n" +
            "Eₜᵦ = $gasKTB\n"
}