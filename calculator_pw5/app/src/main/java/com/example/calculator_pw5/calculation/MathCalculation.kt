package com.example.calculator_pw5.calculation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.math.pow

data class FormState(
    var electricGasSwitchAmount: MutableState<String> = mutableStateOf(""),
    var electricPL100kBlen: MutableState<String> = mutableStateOf(""),
    var converter110kBAmount: MutableState<String> = mutableStateOf(""),
    var switcher10kBAmount: MutableState<String> = mutableStateOf(""),
    var connector10kBamount: MutableState<String> = mutableStateOf(""),
)

data class FormState2(
    var accidentCost: MutableState<String> = mutableStateOf(""),
    var scheduleCost: MutableState<String> = mutableStateOf(""),
    var denyRate: MutableState<String> = mutableStateOf(""),
    var avgAccidentDenyTine: MutableState<String> = mutableStateOf(""),
    var avgScheduleDenyTine: MutableState<String> = mutableStateOf(""),
)


fun calculate(data: FormState): String {
    val electricGasSwitchFRate = 0.01
    val electricPL100kBFRate = 0.007
    val converter110kBFRate = 0.015
    val switcher10kBFRate = 0.02
    val connector10kBFRate = 0.03

    val electricGasSwitchRtime = 0.01
    val electricPL100kBRtime = 0.007
    val converter110kBRtime = 0.015
    val switcher10kBRtime = 0.02
    val connector10kBRtime = 0.03


    try {
        val failureRate1wheel = electricGasSwitchFRate * data.electricGasSwitchAmount.value.toDouble() +
                electricPL100kBFRate * data.electricPL100kBlen.value.toDouble() +
                converter110kBFRate * data.converter110kBAmount.value.toDouble() +
                switcher10kBFRate * data.switcher10kBAmount.value.toDouble() +
                connector10kBFRate * data.connector10kBamount.value.toDouble()
        val avgRepairTime = (
                electricGasSwitchFRate * data.electricGasSwitchAmount.value.toDouble() +
                        electricPL100kBFRate * data.electricPL100kBlen.value.toDouble() +
                        converter110kBFRate * data.converter110kBAmount.value.toDouble() +
                        switcher10kBFRate * data.switcher10kBAmount.value.toDouble() +
                        connector10kBFRate * data.connector10kBamount.value.toDouble()
                ) / failureRate1wheel

        val accidentCoeff = failureRate1wheel * avgRepairTime / 8760
        val idleCoeff = 1.2 * 43 / 8760

        val failureRate2wheel = 2 * failureRate1wheel * (accidentCoeff + idleCoeff) + switcher10kBFRate

        var conclusion: String
        if (failureRate1wheel > failureRate2wheel) {
            conclusion = "Двоколова система електропередачі надійніша"
        } else {
            conclusion = "Одноколова система електропередачі надійніша"
        }

        return "Частота відмови одноколової системи: $failureRate1wheel рік^-1\n" +
                "Частота відмови двоколової системи: $failureRate2wheel рік^-1\n" +
                "Висновок: $conclusion\n"

    } catch (e: Exception) {

        e.printStackTrace()
        return "Something went wrong."
    }
}

fun calculate2(data: FormState2): String {

    try {
        val mathHopeAccident = data.denyRate.value.toDouble() * data.avgAccidentDenyTine.value.toDouble() * 5120 * 6451
        val mathHopeSchedule = data.avgScheduleDenyTine.value.toDouble() * 5120 * 6451

        val mathHopeAccidentCost = mathHopeAccident*data.accidentCost.value.toDouble()

        val mathHopeScheduleCost = mathHopeSchedule*data.scheduleCost.value.toDouble()

        val mathHopeAtAllCost = mathHopeAccident*data.accidentCost.value.toDouble() + mathHopeSchedule*data.scheduleCost.value.toDouble()


        return "Математичне сподівання аварійного невідпущення елек-енергії: $mathHopeAccident кВт*год\n" +
                "Математичне сподівання планового невідпущення елек-енергії: $mathHopeSchedule кВт*год\n" +
                "Математичне сподівання збитків від аварійного переривання: $mathHopeAccidentCost грн\n"+
                "Математичне сподівання збитків від планового переривання: $mathHopeScheduleCost грн\n"+
                "Математичне сподівання загалом: $mathHopeAtAllCost грн\n"

    } catch (e: Exception) {
        e.printStackTrace()
        return "Something went wrong."
    }
}
