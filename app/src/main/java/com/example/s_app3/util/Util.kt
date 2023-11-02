package com.example.s_app3.util

fun CalculateTotalTip(
    titleBill: Double,
    tipPersentage: Int
): Double {
    return if (titleBill > 1&&
        titleBill.toString().isNotEmpty())
        (titleBill*tipPersentage) /100 else 0.0

}
fun calculateTotalPerperson(
    titleBill:Double,
    splitBy:Int,
    tipPersentage: Int
):Double{
    val bill= CalculateTotalTip(
        titleBill=titleBill,
        tipPersentage=tipPersentage
    ) + titleBill
    return (bill / splitBy)
}