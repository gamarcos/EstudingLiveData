package br.com.gabrielmarcos.estudinglivedata.base.view.util

import android.content.Context
import br.com.gabrielmarcos.estudinglivedata.R
import java.util.Locale

class MoneyUtils {

    companion object {
        const val CURRENCY = "R$"
    }

    fun formatMoneyToString(value: String): String {
        val currency = parseMoneyToInteger(value)
        return formatMoneyToString(currency)
    }

    fun formatMoneyToString(value: Int): String {
        return String.format("%s %s", CURRENCY, parseMoneyToString(value))
    }

    fun parseMoneyToInteger(value: String): Int {
        var parsed: Int

        try {
            val s = value.replace("\\D+".toRegex(), "")
            parsed = Integer.parseInt(s)
        } catch (ex: Exception) {
            parsed = 0
        }

        return parsed
    }

    fun parseMoneyToString(value: Int): String {
        return parseMoneyToString(value.toDouble() / 100)
    }

    fun parseMoneyToString(value: Double): String {
        val valueString = String.format(Locale.ENGLISH, "%.2f", value)
        val radixLoc = getRadix(valueString)

        if (radixLoc < 0) {
            return "$value,00" //No cents
        } else {
            val money = valueString.substring(0, radixLoc)
            val cents = valueString.substring(radixLoc + 1, valueString.length)
            return "$money,$cents"
        }
    }

    fun getFractionalPart(value: Double): String {
        val valueString = String.format(Locale.ENGLISH, "%.2f", value)
        val radixLoc = getRadix(valueString)

        return if (radixLoc < 0) {
            "00"
        } else valueString.substring(radixLoc + 1, valueString.length)
    }

    private fun getRadix(valueString: String): Int {
        return valueString.indexOf('.')
    }

    fun parseCurrency(value: Long, context: Context?): String {
        val conversion = value.toDouble() * 0.01
        return context?.getString(R.string.currency_two_precision, conversion)
            ?: String.format("%s %.2f", CURRENCY, conversion)
    }

    fun parseCurrency(value: Long): String {
        val conversion = value.toDouble() * 0.01
        return String.format("%s %.2f", CURRENCY, conversion)
    }

    fun parseMonetaryValue(value: Long): String {
        val conversion = value.toDouble() * 0.01
        return String.format("%.2f", conversion)
    }
}