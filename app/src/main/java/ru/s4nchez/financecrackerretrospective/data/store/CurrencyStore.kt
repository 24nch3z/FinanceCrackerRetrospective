package ru.s4nchez.financecrackerretrospective.data.store

import android.content.Context
import ru.s4nchez.financecrackerretrospective.R

object CurrencyStore {

    val listIds = listOf(
            "RUB",
            "EUR",
            "USD"
    )

    private val names = mapOf(
            "RUB" to R.string.currency_name_rub,
            "EUR" to R.string.currency_name_eur,
            "USD" to R.string.currency_name_usd
    )

    private val valueFormats = mapOf(
            "RUB" to R.string.currency_symbol_format_rub,
            "EUR" to R.string.currency_symbol_format_eur,
            "USD" to R.string.currency_symbol_format_usd
    )

    fun getNamesMap() = names

    fun getNameById(id: String, context: Context) = names[id]?.let { context.getString(it) }
            ?: throw NullPointerException("Отсутсвует строковый ресурс")

    fun getValueFormatById(id: String, context: Context, value: String) = valueFormats[id]
            ?.let { context.getString(it, value) }
            ?: throw NullPointerException("Отсутсвует строковый ресурс")
}