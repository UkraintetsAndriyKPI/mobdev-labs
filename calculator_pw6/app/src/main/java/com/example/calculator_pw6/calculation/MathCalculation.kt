package com.example.calculator_pw6.calculation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.collections.forEach
import kotlin.math.pow
import kotlin.math.sqrt

data class FormState(
    var nominalPowerGrindingMachine: MutableState<String> = mutableStateOf(""),
    var usageCoeffPolishingMachine: MutableState<String> = mutableStateOf(""),
    var reactivePowerCoeffCircularSaw: MutableState<String> = mutableStateOf(""),
)

var KKDnominal = 0.92
var powerCoefficient = 0.9
var loadVoltage = 0.38

val allEP = arrayOf(
    "GrindingMachine",
    "DrillingMachine",
    "GroutingMachine",
    "CircularSaw",
    "Press",
    "PolishingMachine",
    "MillingMachine",
    "Fan",
)

val amountEP: MutableMap<String, Int> = mutableMapOf(
    "GrindingMachine" to 4,
    "DrillingMachine" to 2,
    "GroutingMachine" to 4,
    "CircularSaw" to 1,
    "Press" to 1,
    "PolishingMachine" to 1,
    "MillingMachine" to 2,
    "Fan" to 1,
    "WeldingTransformer" to 2,
    "DryerWardrobe" to 2,
)

val nominalPower: MutableMap<String, Int> = mutableMapOf(
    "GrindingMachine" to 20,
    "DrillingMachine" to 14,
    "GroutingMachine" to 42,
    "CircularSaw" to 36,
    "Press" to 20,
    "PolishingMachine" to 40,
    "MillingMachine" to 32,
    "Fan" to 20,
    "WeldingTransformer" to 100,
    "DryerWardrobe" to 120,
)

val usageCoeff: MutableMap<String, Double> = mutableMapOf(
    "GrindingMachine" to 0.15,
    "DrillingMachine" to 0.12,
    "GroutingMachine" to 0.15,
    "CircularSaw" to 0.3,
    "Press" to 0.5,
    "PolishingMachine" to 0.2,
    "MillingMachine" to 0.2,
    "Fan" to 0.65,
    "WeldingTransformer" to 0.2,
    "DryerWardrobe" to 0.8,
)

val reactivePowerCoeff: MutableMap<String, Double> = mutableMapOf(
    "GrindingMachine" to 1.33,
    "DrillingMachine" to 1.0,
    "GroutingMachine" to 1.33,
    "CircularSaw" to 1.52,
    "Press" to 0.75,
    "PolishingMachine" to 1.0,
    "MillingMachine" to 1.0,
    "Fan" to 0.75,
    "WeldingTransformer" to 3.0,
    "DryerWardrobe" to 0.0,
)

val stream1stLevel: MutableMap<String, Double> = mutableMapOf(
    "GrindingMachine" to 0.0,
    "DrillingMachine" to 0.0,
    "GroutingMachine" to 0.0,
    "CircularSaw" to 0.0,
    "Press" to 0.0,
    "PolishingMachine" to 0.0,
    "MillingMachine" to 0.0,
    "Fan" to 0.0,
    "WeldingTransformer" to 0.0,
    "DryerWardrobe" to 0.0,
)

val calculatedGroupStream: MutableMap<String, Double> = mutableMapOf(
    "GrindingMachine" to 0.0,
    "DrillingMachine" to 0.0,
    "GroutingMachine" to 0.0,
    "CircularSaw" to 0.0,
    "Press" to 0.0,
    "PolishingMachine" to 0.0,
    "MillingMachine" to 0.0,
    "Fan" to 0.0,
    "WeldingTransformer" to 0.0,
    "DryerWardrobe" to 0.0,
)

val nPnKB: MutableMap<String, Double> = mutableMapOf(
    "GrindingMachine" to 0.0,
    "DrillingMachine" to 0.0,
    "GroutingMachine" to 0.0,
    "CircularSaw" to 0.0,
    "Press" to 0.0,
    "PolishingMachine" to 0.0,
    "MillingMachine" to 0.0,
    "Fan" to 0.0,
    "WeldingTransformer" to 0.0,
    "DryerWardrobe" to 0.0,
)

val nPnKBtg: MutableMap<String, Double> = mutableMapOf(
    "GrindingMachine" to 0.0,
    "DrillingMachine" to 0.0,
    "GroutingMachine" to 0.0,
    "CircularSaw" to 0.0,
    "Press" to 0.0,
    "PolishingMachine" to 0.0,
    "MillingMachine" to 0.0,
    "Fan" to 0.0,
    "WeldingTransformer" to 0.0,
    "DryerWardrobe" to 0.0,
)

val nPnpow2: MutableMap<String, Double> = mutableMapOf(
    "GrindingMachine" to 0.0,
    "DrillingMachine" to 0.0,
    "GroutingMachine" to 0.0,
    "CircularSaw" to 0.0,
    "Press" to 0.0,
    "PolishingMachine" to 0.0,
    "MillingMachine" to 0.0,
    "Fan" to 0.0,
    "WeldingTransformer" to 0.0,
    "DryerWardrobe" to 0.0,
)

fun calculate(data: FormState): String {

    try {
        updateData(data)
        // 3.1 - 3.2
        amountEP.forEach { (key, value) ->
            println("$key -> $value")
            stream1stLevel[key] = (amountEP[key]!! * nominalPower[key]!!).toDouble()
            calculatedGroupStream[key] =
                (stream1stLevel[key]!! / (3.0.pow(0.5) * loadVoltage * powerCoefficient * KKDnominal))
        }


        amountEP.forEach { (key, _) ->
            nPnKB[key] = amountEP[key]!! * nominalPower[key]!! * usageCoeff[key]!!
            nPnKBtg[key] = amountEP[key]!! * nominalPower[key]!! * usageCoeff[key]!! * reactivePowerCoeff[key]!!
            nPnpow2[key] = amountEP[key]!! * nominalPower[key]!!.toDouble().pow(2)
        }


        val efectiveEPamount =
            (stream1stLevel.values.sum() - stream1stLevel["WeldingTransformer"]!! - stream1stLevel["DryerWardrobe"]!!)
                .pow(2) / getSHRnPnpow2SUM()

        val calculatedActiveLoad = 1.25 * getSHRnPnKBSUM()
        val fullPower = sqrt(calculatedActiveLoad.pow(2) + getSHRnPnKBtgSUM().pow(2))

//        -------------------

        val cehEPEfectiveAmount =
            (stream1stLevel.values.sum() +
                    (stream1stLevel.values.sum() - (stream1stLevel["WeldingTransformer"]!! + stream1stLevel["DryerWardrobe"]!!)) * 3).pow(
                2
            ) / (nPnpow2.values.sum() + getSHRnPnpow2SUM() * 2)
        val cehUsageCoeff = nPnKB.values.sum() / stream1stLevel.values.sum()

        val cehUsageCoeffAtAll = 0.7
        val calculatedActiveLoadOnTires = cehUsageCoeffAtAll * (cehUsageCoeff * (stream1stLevel.values.sum() +
                (stream1stLevel.values.sum() - (stream1stLevel["WeldingTransformer"]!! + stream1stLevel["DryerWardrobe"]!!)) * 3))
        val calculatedReactiveLoadOnTires = cehUsageCoeffAtAll * (nPnKBtg.values.sum() + getSHRnPnKBtgSUM() * 4)

        val fullPowerOnTires = sqrt(calculatedActiveLoadOnTires.pow(2) + calculatedReactiveLoadOnTires.pow(2))

        val calculatedCurrentOnTires = calculatedActiveLoadOnTires / loadVoltage

        return "Ефективна к-сть ЕП ШР1=ШР2=ШР3: $efectiveEPamount \n" +
                "Розрахунковий коеф. акив. потуж. ШР1=ШР2=ШР3: 1.25 \n" +
                "Розрахункове акив. навант. ШР1=ШР2=ШР3: $calculatedActiveLoad \n" +
                "Розрахункове реактивне навант. ШР1=ШР2=ШР3: ${getSHRnPnKBtgSUM()} \n" +
                "Повна потужність ШР1=ШР2=ШР3: $fullPower \n" +
                "----------------------------------------\n" +
//                "${nPnKB.values.sum()+getSHRnPnKBSUM()*4} \n" +
                "Ефективна к-сть ЕП : $cehEPEfectiveAmount \n" +
                "Коеф. використання всього цеху: $cehUsageCoeff \n" +
                "Розрахунковий коеф. актив. потужності цеху вцілому: $cehUsageCoeffAtAll \n" +
                "Розрахунковий актив. навантаження на шинах 0.38 кВ ТП: $calculatedActiveLoadOnTires кВТ\n" +
                "Розрахунковий реактив. навантаження на шинах 0.38 кВ ТП: $calculatedReactiveLoadOnTires квар\n" +
                "Повна потужність шинах 0.38 кВ ТП: $fullPowerOnTires кВ*А\n" +
                "Розрахунковий груп. струм на шинах 0.38 кВ ТП: $calculatedCurrentOnTires А\n"


    } catch (e: Exception) {

        e.printStackTrace()
        return "Something went wrong."
    }
}

fun updateData(data: FormState) {
    if (data.nominalPowerGrindingMachine.value != "") {
        nominalPower["GrindingMachine"] = data.nominalPowerGrindingMachine.value.toInt()
    }
    if (data.usageCoeffPolishingMachine.value != "") {
        usageCoeff["PolishingMachine"] = data.usageCoeffPolishingMachine.value.toDouble()
    }
    if (data.reactivePowerCoeffCircularSaw.value != "") {
        reactivePowerCoeff["CircularSaw"] = data.reactivePowerCoeffCircularSaw.value.toDouble()
    }
}


fun getSHRUsageCoeff(): Double {
    var sumnPk = 0.0
    var sumnP = 0.0

    allEP.forEach { key ->
        sumnPk += amountEP[key]!! * nominalPower[key]!! * usageCoeff[key]!!
        sumnP += amountEP[key]!! * nominalPower[key]!!
    }

    val coeff = sumnPk / sumnP
    println("SHR Usage coefficient: $coeff")
    return coeff
}

fun getSHRGroupStream(bigP: Double): Double {
    return bigP / loadVoltage
}

fun getSHRnPnKBSUM(): Double {
    var sumnPnKB = 0.0
    allEP.forEach { key ->
        sumnPnKB += nPnKB[key]!!
    }

    println("SHR nPnKBSUM: $sumnPnKB")
    return sumnPnKB
}

fun getSHRnPnKBtgSUM(): Double {
    var sumnPnKBtg = 0.0
    allEP.forEach { key ->
        sumnPnKBtg += nPnKBtg[key]!!
    }

    println("SHR nPnKBtgSUM: $sumnPnKBtg")
    return sumnPnKBtg
}

fun getSHRnPnpow2SUM(): Double {
    var sumnPnpow2 = 0.0
    allEP.forEach { key ->
        sumnPnpow2 += nPnpow2[key]!!
    }

    println("SHR nPnpow2SUM: $sumnPnpow2")
    return sumnPnpow2
}

