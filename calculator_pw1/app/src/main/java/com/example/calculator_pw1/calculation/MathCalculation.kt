package com.example.calculator_pw1.calculation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class FormState(
    var valueHP: MutableState<String> = mutableStateOf(""),
    var valueCP: MutableState<String> = mutableStateOf(""),
    var valueSP: MutableState<String> = mutableStateOf(""),
    var valueNP: MutableState<String> = mutableStateOf(""),
    var valueOP: MutableState<String> = mutableStateOf(""),
    var valueWP: MutableState<String> = mutableStateOf(""),
    var valueAP: MutableState<String> = mutableStateOf(""),
)

data class FormState2(
    var valueHG: MutableState<String> = mutableStateOf(""),
    var valueCG: MutableState<String> = mutableStateOf(""),
    var valueSG: MutableState<String> = mutableStateOf(""),
    var valueNG: MutableState<String> = mutableStateOf(""),
    var valueOG: MutableState<String> = mutableStateOf(""),
    var valueWG: MutableState<String> = mutableStateOf(""),
    var valueAG: MutableState<String> = mutableStateOf(""),
    var valueQI: MutableState<String> = mutableStateOf(""),
    var valueVG: MutableState<String> = mutableStateOf(""),
)

fun calculate(data: FormState): String {
    var resultKPC: Double?
    var resultKPG: Double?

    var resultHC: Double?
    var resultCC: Double?
    var resultSC: Double?
    var resultNC: Double?
    var resultOC: Double?
    var resultAC: Double?

    var resultHG: Double?
    var resultCG: Double?
    var resultSG: Double?
    var resultNG: Double?
    var resultOG: Double?

    var resultQPH: Double?
    var resultQCH: Double?
    var resultQGH: Double?


    try {
    resultKPC = 100 / (100 - data.valueWP.value.toDouble())
    resultKPG = 100 / (100 - data.valueWP.value.toDouble() - data.valueAP.value.toDouble())

    resultHC = data.valueHP.value.toDouble() * resultKPC
    resultCC = data.valueCP.value.toDouble() * resultKPC
    resultSC = data.valueSP.value.toDouble() * resultKPC
    resultNC = data.valueNP.value.toDouble() * resultKPC
    resultOC = data.valueOP.value.toDouble() * resultKPC
    resultAC = data.valueAP.value.toDouble() * resultKPC

    resultHG = data.valueHP.value.toDouble() * resultKPG
    resultCG = data.valueCP.value.toDouble() * resultKPG
    resultSG = data.valueSP.value.toDouble() * resultKPG
    resultNG = data.valueNP.value.toDouble() * resultKPG
    resultOG = data.valueOP.value.toDouble() * resultKPG

    resultQPH = (339 * data.valueCP.value.toDouble() +
            1030 * data.valueHP.value.toDouble() -
            108.8 * (data.valueOP.value.toDouble() - data.valueSP.value.toDouble()) -
            25 * data.valueWP.value.toDouble()) / 1000

    resultQCH = (resultQPH + 0.025 * data.valueWP.value.toDouble()) * (100 / (100 - data.valueWP.value.toDouble()))

    resultQGH = (resultQPH + 0.025 * data.valueWP.value.toDouble()) *
            (100 / (100 - data.valueWP.value.toDouble() - data.valueAP.value.toDouble()))

    } catch (e: Exception) {

        e.printStackTrace()
        return "Something went wrong."
    }

    Log.d("CHECKing", (resultHC + resultCC + resultSC + resultNC + resultOC + resultAC).toString())
    Log.d("resultKPC", resultKPC.toString())
    Log.d("resultKPG", resultKPG.toString())

    Log.d("StartInfo", "Calculate Function call")
    Log.d("DATA", data.toString())


    Log.d("CALC", "resultKPC = $resultKPC")
    Log.d("CALC", "resultKPG = $resultKPG")

    Log.d("CALC", "resultHC = $resultHC")
    Log.d("CALC", "resultCC = $resultCC")
    Log.d("CALC", "resultSC = $resultSC")
    Log.d("CALC", "resultNC = $resultNC")
    Log.d("CALC", "resultOC = $resultOC")
    Log.d("CALC", "resultAC = $resultAC")

    Log.d("CALC", "resultHG = $resultHG")
    Log.d("CALC", "resultCG = $resultCG")
    Log.d("CALC", "resultSG = $resultSG")
    Log.d("CALC", "resultNG = $resultNG")
    Log.d("CALC", "resultOG = $resultOG")

    Log.d("CALC", "resultQPH = $resultQPH")
    Log.d("CALC", "resultQCH = $resultQCH")
    Log.d("CALC", "resultQGH = $resultQGH")

    return "Kᴾᶜ = $resultKPC\n" +
            "Kᴾᴳ = $resultKPG\n" +

            "Hᶜ = $resultHC\n" +
            "Cᶜ = $resultCC\n" +
            "Sᶜ = $resultSC\n" +
            "Nᶜ = $resultNC\n" +
            "Oᶜ = $resultOC\n" +
            "Aᶜ = $resultAC\n" +

            "Hᴳ = $resultHG\n" +
            "Cᴳ = $resultCG\n" +
            "Sᴳ = $resultSG\n" +
            "Nᴳ = $resultNG\n" +
            "Oᴳ = $resultOG\n" +

            "Qᴾₕ = $resultQPH\n" +
            "Qᶜₕ = $resultQCH\n" +
            "Qᴳₕ = $resultQGH\n"
}


fun calculate2(data: FormState2): String {
    var resultHP: Double?
    var resultCP: Double?
    var resultSP: Double?
    var resultOP: Double?
    var resultAP: Double?

    var resultQPI: Double?

    Log.d("StartInfo", "Calculate Function2 call")
    Log.d("DATA", data.toString())

    try {
    resultHP =
        (data.valueHG.value.toDouble() * (100 - data.valueWG.value.toDouble() - data.valueAG.value.toDouble())) / 100

    resultCP =
        (data.valueCG.value.toDouble() * (100 - data.valueWG.value.toDouble() - data.valueAG.value.toDouble())) / 100

    resultSP =
        (data.valueSG.value.toDouble() * (100 - data.valueWG.value.toDouble() - data.valueAG.value.toDouble())) / 100

    resultOP =
        (data.valueOG.value.toDouble() * (100 - data.valueWG.value.toDouble() - data.valueAG.value.toDouble())) / 100

    resultAP = (data.valueAG.value.toDouble() * (100 - data.valueWG.value.toDouble())) / 100

    resultQPI =
        data.valueQI.value.toDouble() * ((100 - data.valueWG.value.toDouble() - data.valueAG.value.toDouble()) / 100) -
        0.025 * data.valueWG.value.toDouble()
    } catch (e: Exception) {

        e.printStackTrace()
        return "Something went wrong."
    }

    return  "Hᴾ = $resultHP\n" +
            "Cᴾ = $resultCP\n" +
            "Sᴾ = $resultSP\n" +
            "Oᴾ = $resultOP\n" +
            "Aᴾ = $resultAP\n" +

            "Qᴾᵢ = $resultQPI\n"
}